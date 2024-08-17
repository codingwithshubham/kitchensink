package com.example.kitchensink.service;

import com.example.kitchensink.entity.MemberEntity;

import java.util.List;

public interface MemberService {
  List<MemberEntity> listAllMembers();

  MemberEntity lookupMemberById(long id);

  void createMember(MemberEntity memberEntity);
}
