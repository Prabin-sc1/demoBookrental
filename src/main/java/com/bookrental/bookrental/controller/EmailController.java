//package com.bookrental.bookrental.controller;
//
//import com.bookrental.bookrental.pojo.EmailRequestPojo;
//import com.bookrental.bookrental.service.email.NewEmailService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/email")
//public class EmailController {
//
//    private final NewEmailService newEmailService;
//
//    public EmailController(NewEmailService newEmailService) {
//        this.newEmailService = newEmailService;
//    }
//
//    @PostMapping
//    public ResponseEntity send(@RequestBody EmailRequestPojo emailRequestPojo) {
//        newEmailService.sendEmail(emailRequestPojo.getTo(), emailRequestPojo.getSubject(), emailRequestPojo.getMessage());
//        return new ResponseEntity(HttpStatus.CREATED);
//    }
//}
