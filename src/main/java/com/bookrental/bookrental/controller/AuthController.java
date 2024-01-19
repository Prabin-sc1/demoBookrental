package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.jwt.JwtHelper;
import com.bookrental.bookrental.pojo.ChangePasswordRequest;
import com.bookrental.bookrental.pojo.jwt.JwtRequestPojo;
import com.bookrental.bookrental.pojo.jwt.JwtResponsePojo;
import com.bookrental.bookrental.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserDetailsService userDetailsService;

    private final AuthenticationManager manager;

    private final JwtHelper helper;

    private final UserService userService;

    public AuthController(UserDetailsService userDetailsService, AuthenticationManager manager, JwtHelper helper, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.manager = manager;
        this.helper = helper;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponsePojo> login(@RequestBody JwtRequestPojo request) {
        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponsePojo response = JwtResponsePojo.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*@PostMapping("/change-password")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordRequest request, Principal principal) {
        Boolean success = userService.changePassword(request.getOldPassword(), request.getNewPassword(), principal);
        return ResponseEntity.ok(success);
    }*/

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
