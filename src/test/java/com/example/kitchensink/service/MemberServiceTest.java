package com.example.kitchensink.service;

import com.example.kitchensink.entity.MemberEntity;
import com.example.kitchensink.exception.ResourceNotFoundException;
import com.example.kitchensink.repository.MemberRepository;
import com.example.kitchensink.service.impl.MemberServiceImpl;
import jakarta.validation.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {

  @InjectMocks
  private MemberServiceImpl memberService;

  @Mock
  private MemberRepository memberRepository;

  @Test
  public void testListAllMembers() {
    List<MemberEntity> dummyMemberEntities = new ArrayList<>();
    dummyMemberEntities.add(new MemberEntity("Jane Doe", "jane@mailinator.com", "2125551234"));
    when(memberRepository.findAllByOrderByName()).thenReturn(dummyMemberEntities);

    List<MemberEntity> memberEntities = memberService.listAllMembers();
    assertNotNull(memberEntities);
    assertEquals(1, memberEntities.size());
    assertEquals(dummyMemberEntities, memberEntities);
  }

  @Test
  public void testPositiveLookupMemberById() {
    MemberEntity dummyMember = new MemberEntity("Jane Doe", "jane@mailinator.com", "2125551234");
    when(memberRepository.findById(1l))
        .thenReturn(Optional.of(dummyMember));

    MemberEntity memberEntity = memberService.lookupMemberById(1l);
    assertNotNull(memberEntity);
    assertEquals(dummyMember, memberEntity);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void testNegativeLookupMemberById() {
    when(memberRepository.findById(1l))
        .thenThrow(ResourceNotFoundException.class);

    memberService.lookupMemberById(1l);
  }

  @Test
  public void testPositiveCreateMember() {
    MemberEntity newMember = new MemberEntity();
    newMember.setName("Jane Doe");
    newMember.setEmail("jane@mailinator.com");
    newMember.setPhoneNumber("2125551234");

    when(memberRepository.findByEmail("jane@mailinator.com"))
        .thenReturn(null);
    when(memberRepository.save(newMember)).thenReturn(newMember);

    MemberEntity member = memberService.createMember(newMember);
    assertEquals(newMember, member);
  }

  @Test(expected = ValidationException.class)
  public void testCreateMemberWithEmailAlreadyExist() {
    MemberEntity newMember = new MemberEntity();
    newMember.setName("Jane Doe");
    newMember.setEmail("jane@mailinator.com");
    newMember.setPhoneNumber("2125551234");

    when(memberRepository.findByEmail("jane@mailinator.com"))
        .thenReturn(newMember);

    memberService.createMember(newMember);
  }

  @Test
  public void testPositiveUpdateMember() {
    MemberEntity member = new MemberEntity();
    member.setName("Jane Doe");
    member.setEmail("jane@mailinator.com");
    member.setPhoneNumber("2125551234");

    when(memberRepository.save(member))
        .thenReturn(member);

    MemberEntity updatedMember = memberService.updateMember(member);
    assertEquals(updatedMember, member);
  }

  @Test
  public void testPositiveMemberExistsByIdMember() {
    when(memberRepository.existsById(1l))
        .thenReturn(true);
    assertTrue(memberService.memberExistsById(1l));
  }

  @Test
  public void testNegativeMemberExistsByIdMember() {
    when(memberRepository.existsById(1l))
        .thenReturn(false);
    assertFalse(memberService.memberExistsById(1l));
  }
}
