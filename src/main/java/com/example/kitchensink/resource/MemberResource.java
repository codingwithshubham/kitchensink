package com.example.kitchensink.resource;

import com.example.kitchensink.entity.MemberEntity;
import com.example.kitchensink.service.MemberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@Slf4j
public class MemberResource {

  @Autowired
  private MemberService memberService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MemberEntity>> getAllMembers() {
    List<MemberEntity> memberEntities = memberService.listAllMembers();
    log.info("Returning {} members", memberEntities.size());
    return new ResponseEntity<>(memberEntities, HttpStatus.OK);
  }

  @GetMapping(value = "/{id:[0-9][0-9]*}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MemberEntity> getMemberById(@PathVariable("id") long id) {
    MemberEntity memberEntity = memberService.lookupMemberById(id);
    log.info("Returning member with id {}", id);
    return new ResponseEntity<>(memberEntity, HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> addMember(@Valid @RequestBody MemberEntity memberEntity) {
    memberService.createMember(memberEntity);
    log.info("Member created successfully");
    return new ResponseEntity<>("Member created successfully", HttpStatus.OK);
  }

}
