package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.generic.GlobalApiResponse;
import com.bookrental.bookrental.pojo.member.MemberRequestPojo;
import com.bookrental.bookrental.pojo.member.MemberResponsePojo;
import com.bookrental.bookrental.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

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
    @Operation(
            summary = "Create and Update Member",
            description = "This end point used to create and update member",
            responses = @ApiResponse(responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = MemberRequestPojo.class))
                    }
            )
    )
    public ResponseEntity<GlobalApiResponse> createMember(@Valid @RequestBody MemberRequestPojo memberRequestPojo) {
        memberService.createMember(memberRequestPojo);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.SAVE.getCode(), module), null));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get member by id",
            description = "This end point can be used for getting member by id",
            responses = @ApiResponse(responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = MemberResponsePojo.class))
                    }
            )
    )
    public ResponseEntity<GlobalApiResponse> memberById(@PathVariable Integer id) {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIEVE.getCode(), module),
                memberService.getMemberById(id)));
    }

    @GetMapping
    @Operation(
            summary = "Retrieve all members",
            responses = {
                    @ApiResponse(responseCode = "200", content = {@Content
                            (array = @ArraySchema
                                    (schema = @Schema(implementation = MemberResponsePojo.class)))},
                            description = "This end point fetch all members"
                    )
            }
    )
    public ResponseEntity<GlobalApiResponse> allMember() {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIVE_ALL.getCode(), module), memberService.getAllMember()));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Member",
            description = "This end point used to delete member",
            responses = @ApiResponse(responseCode = "200"
            )
    )
    public ResponseEntity<GlobalApiResponse> deleteById(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.DELETE.getCode(), module), null));
    }

    @GetMapping("/member-excel-data")
    public ResponseEntity<Resource> download() throws IOException {
        String fileName = "member.xlsx";
        ByteArrayInputStream bis = memberService.getExcelData();
        InputStreamResource file = new InputStreamResource(bis);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }


    @PostMapping("/upload")
    public ResponseEntity<GlobalApiResponse> saveTransaction(@RequestParam("file") MultipartFile multipartFile) {
        memberService.save(multipartFile);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.SAVE.getCode(), module),
                null));
    }
}
