package com.example.kitchensink.handler;

import com.example.kitchensink.exception.ResourceNotFoundException;
import com.example.kitchensink.exception.handler.GlobalExceptionHandler;
import jakarta.validation.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {

  @InjectMocks
  private GlobalExceptionHandler globalExceptionHandler;

  @Test
  public void testHandleResourceNotFoundException() {
    ResourceNotFoundException ex = new ResourceNotFoundException("Resource not found");
    ResponseEntity<String> responseEntity = globalExceptionHandler.handleResourceNotFoundException(ex);
    assertNotNull(responseEntity);
    assertEquals("Resource not found", responseEntity.getBody());
    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
  }

  @Test
  public void testHandleValidationException() {
    ValidationException ex = new ValidationException("Unique Email Violation");
    ResponseEntity<String> responseEntity = globalExceptionHandler.handleValidationException(ex);
    assertNotNull(responseEntity);
    assertEquals("Unique Email Violation", responseEntity.getBody());
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }

  @Test
  public void testHandleArgumentNotValidException() {
    MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
    BindingResult bindingResult = mock(BindingResult.class);
    when(ex.getBindingResult()).thenReturn(bindingResult);
    List<ObjectError> objectErrors = List.of(new FieldError("", "email", "Unique Email Violation"));
    when(bindingResult.getAllErrors()).thenReturn(objectErrors);
    Map<String, String> expectedErrors = new HashMap<>();
    expectedErrors.put("email", "Unique Email Violation");

    ResponseEntity<Map<String, String>> responseEntity = globalExceptionHandler.handleArgumentNotValidException(ex);
    assertNotNull(responseEntity);
    assertEquals(expectedErrors, responseEntity.getBody());
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }

  @Test
  public void testHandleGlobalException() {
    RuntimeException ex = new RuntimeException("Exception occurred");
    ResponseEntity<String> responseEntity = globalExceptionHandler.handleGlobalException(ex);
    assertNotNull(responseEntity);
    assertEquals("An unexpected error occurred", responseEntity.getBody());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
  }
}
