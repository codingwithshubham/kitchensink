package com.example.kitchensink.repository;

import com.example.kitchensink.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  // Method to find all users ordered by name
  List<MemberEntity> findAllByOrderByName();

  MemberEntity findByEmail(String email);
}
