package com.example.kitchensink.service;

import com.example.kitchensink.entity.MemberEntity;

import java.util.List;

public interface MemberService {
  List<MemberEntity> listAllMembers();

  MemberEntity lookupMemberById(long id);

  MemberEntity createMember(MemberEntity memberEntity);

  boolean memberExistsById(Long id);

  MemberEntity updateMember(MemberEntity memberEntity);

  void deleteById(Long id);
}
