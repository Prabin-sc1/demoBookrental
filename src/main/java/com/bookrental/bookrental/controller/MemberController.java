package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.pojo.MemberRequestPojo;
import com.bookrental.bookrental.service.member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // create
    @PostMapping
    public ResponseEntity<MemberRequestPojo> createMember(@RequestBody MemberRequestPojo memberRequestPojo) {
        this.memberService.createMember(memberRequestPojo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/home")
    public String home() {
        return "ho fasfs me";
    }

}
