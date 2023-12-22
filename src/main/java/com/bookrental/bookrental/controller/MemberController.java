package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.pojo.member.MemberRequestPojo;
import com.bookrental.bookrental.pojo.member.MemberResponsePojo;
import com.bookrental.bookrental.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberRequestPojo> createMember(@RequestBody MemberRequestPojo memberRequestPojo) {
        memberService.createMember(memberRequestPojo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponsePojo> memberById(@PathVariable Integer id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponsePojo>> allMember() {
        return ResponseEntity.ok(memberService.getAllMember());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MemberResponsePojo> deleteById(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
