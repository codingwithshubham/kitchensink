package com.example.kitchensink.service.impl;

import com.example.kitchensink.entity.MemberEntity;
import com.example.kitchensink.exception.ResourceNotFoundException;
import com.example.kitchensink.repository.MemberRepository;
import com.example.kitchensink.service.MemberService;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<MemberEntity> listAllMembers() {
        log.info("fetching all members");
        return memberRepository.findAllByOrderByName();
    }

    @Override
    public MemberEntity lookupMemberById(long id) {
        log.info("fetching member by id {}", id);
        return memberRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public MemberEntity createMember(MemberEntity memberEntity) {
        // Check the uniqueness of the email address
        if (emailAlreadyExists(memberEntity.getEmail())) {
            throw new ValidationException("Email Id already exists. Please use a different email id.");
        }
        log.info("Registering member {}", memberEntity.getName());
        return memberRepository.save(memberEntity);
    }

    @Override
    public MemberEntity updateMember(MemberEntity memberEntity) {
        return memberRepository.save(memberEntity);
    }

    @Override
    public boolean memberExistsById(Long id) {
        return memberRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    /**
     * Checks if a member with the same email address is already registered. This is the case if the email address is already in the database.
     *
     * @param email The email to check
     * @return True if the email already exists, and false otherwise
     */
    public boolean emailAlreadyExists(String email) {
        MemberEntity memberEntity = null;
        try {
            memberEntity = memberRepository.findByEmail(email);
        } catch (Exception e) {
            // ignore
        }
        return memberEntity != null;
    }
}
