package com.example.kitchensink.resource;

import com.example.kitchensink.entity.MemberEntity;
import com.example.kitchensink.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemberResourceTest {

  @InjectMocks
  private MemberResource memberResource;

  @Mock
  private MemberService memberService;

  @Test
  public void testPositiveGetAllMembers() {
    List<MemberEntity> dummyMemberEntities = new ArrayList<>();
    dummyMemberEntities.add(new MemberEntity("Jane Doe", "jane@mailinator.com", "2125551234"));
    when(memberService.listAllMembers()).thenReturn(dummyMemberEntities);

    ResponseEntity<List<MemberEntity>> responseEntity = memberResource.getAllMembers();
    assertNotNull(responseEntity);
    assertNotNull(responseEntity.getBody());
    assertEquals(1, responseEntity.getBody().size());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void testPositiveGetMemberById() {
    MemberEntity dummyMember = new MemberEntity("Jane Doe", "jane@mailinator.com", "2125551234");
    when(memberService.lookupMemberById(1)).thenReturn(dummyMember);

    ResponseEntity<MemberEntity> responseEntity = memberResource.getMemberById(1);
    assertNotNull(responseEntity);
    assertNotNull(responseEntity.getBody());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void testNegativeGetMemberById() {
    ResponseEntity<MemberEntity> responseEntity = memberResource.getMemberById(1);
    assertNotNull(responseEntity);
    assertNull(responseEntity.getBody());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void testCreateMember() {
    MemberEntity newMember = new MemberEntity();
    newMember.setName("Jane Doe");
    newMember.setEmail("jane@mailinator.com");
    newMember.setPhoneNumber("2125551234");

    ResponseEntity<String> responseEntity = memberResource.addMember(newMember);
    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }
}
