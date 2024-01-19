package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.config.CustomMessageSource;
import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.jwt.JwtHelper;
import com.bookrental.bookrental.pojo.jwt.JwtRequestPojo;
import com.bookrental.bookrental.pojo.jwt.JwtResponsePojo;
import com.bookrental.bookrental.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = ModuleNameConstants.AUTH)
public class AuthController extends MyBaseController {
    private final UserDetailsService userDetailsService;

    private final AuthenticationManager manager;

    private final JwtHelper helper;

    private final CustomMessageSource customMessageSource;

    public AuthController(UserDetailsService userDetailsService, AuthenticationManager manager,
                          JwtHelper helper, CustomMessageSource customMessageSource) {
        this.userDetailsService = userDetailsService;
        this.manager = manager;
        this.helper = helper;
        this.customMessageSource = customMessageSource;
        this.module = ModuleNameConstants.AUTH;
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

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(customMessageSource.get(Message.CREDENTIAL_INVALID.getCode()));
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return customMessageSource.get(Message.CREDENTIAL_INVALID.getCode());
    }
}
