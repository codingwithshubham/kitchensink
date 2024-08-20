package com.example.kitchensink.resource;

import com.example.kitchensink.entity.MemberEntity;
import com.example.kitchensink.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
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
  public ResponseEntity<MemberEntity> addMember(@Valid @RequestBody MemberEntity memberEntity) {
    if (memberService.memberExistsById(memberEntity.getId())) {
      throw new ValidationException("Member with id " + memberEntity.getId() + " already exists");
    }
    MemberEntity member = memberService.createMember(memberEntity);
    log.info("Member created successfully");
    return new ResponseEntity<>(member, HttpStatus.OK);
  }

  @PutMapping(value = "/{id:[0-9][0-9]*}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MemberEntity> updateMember(@PathVariable("id") long id, @RequestBody MemberEntity memberEntity) {

    MemberEntity existingMember = memberService.lookupMemberById(id);
    existingMember.setName(memberEntity.getName());
    existingMember.setEmail(memberEntity.getEmail());
    existingMember.setPhoneNumber(memberEntity.getPhoneNumber());

    MemberEntity updatedMember = memberService.updateMember(existingMember);
    log.info("Member with id {} updated successfully", id);

    return new ResponseEntity<>(updatedMember, HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id:[0-9][0-9]*}")
  public ResponseEntity<String> deleteMember(@PathVariable("id") long id) {
    if (memberService.memberExistsById(id)) {
      memberService.deleteById(id);
      log.info("Member with id {} deleted successfully", id);
      return new ResponseEntity<>("Member deleted successfully", HttpStatus.OK);  // HTTP 204 No Content
    } else {
      return new ResponseEntity<>("Member not found with given id", HttpStatus.NOT_FOUND);
    }
  }

}
