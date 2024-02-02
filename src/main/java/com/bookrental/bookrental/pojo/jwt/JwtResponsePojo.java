package com.bookrental.bookrental.pojo.jwt;

import lombok.*;

@Getter
@Setter
@Builder
public class JwtResponsePojo {
    private String jwtToken;
    private String username;
}
