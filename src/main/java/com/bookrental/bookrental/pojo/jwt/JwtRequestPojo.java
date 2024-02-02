package com.bookrental.bookrental.pojo.jwt;

import lombok.*;

@Getter
public class JwtRequestPojo {
    private String email;
    private String password;
}
