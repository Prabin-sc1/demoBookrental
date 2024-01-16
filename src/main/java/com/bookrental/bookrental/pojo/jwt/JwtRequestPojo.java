package com.bookrental.bookrental.pojo.jwt;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtRequestPojo {
    private String email;
    private String password;
}
