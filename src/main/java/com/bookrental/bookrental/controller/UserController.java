package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.pojo.user.UserRequestPojo;
import com.bookrental.bookrental.pojo.user.UserResponsePojo;
import com.bookrental.bookrental.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = ModuleNameConstants.USER)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponsePojo> create(@RequestBody UserRequestPojo user) {
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
