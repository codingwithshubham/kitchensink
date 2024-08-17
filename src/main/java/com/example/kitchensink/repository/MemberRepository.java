package com.example.kitchensink.repository;

import com.example.kitchensink.entity.MemberEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends MongoRepository<MemberEntity, Long> {

  // Method to find all users ordered by name
  List<MemberEntity> findAllByOrderByName();

  MemberEntity findByEmail(String email);
}
