package com.bookrental.bookrental.pojo.jwt;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtResponsePojo {
    private String jwtToken;
    private String username;
}
