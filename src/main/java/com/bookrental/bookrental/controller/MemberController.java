package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.generic.GlobalApiResponse;
import com.bookrental.bookrental.pojo.member.MemberRequestPojo;
import com.bookrental.bookrental.pojo.member.MemberResponsePojo;
import com.bookrental.bookrental.service.member.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@Tag(name = ModuleNameConstants.MEMBER)
public class MemberController extends MyBaseController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        this.module = ModuleNameConstants.MEMBER;
    }

    @PostMapping
    public ResponseEntity<GlobalApiResponse> createMember(@Valid @RequestBody MemberRequestPojo memberRequestPojo) {
        memberService.createMember(memberRequestPojo);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.SAVE.getCode(), module), null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> memberById(@PathVariable Integer id) {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIEVE.getCode(), module), memberService.getMemberById(id)));
    }

    @GetMapping
    public ResponseEntity<GlobalApiResponse> allMember() {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIVE_ALL.getCode(), module), memberService.getAllMember()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> deleteById(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.DELETE.getCode(), module), null));
    }
}
