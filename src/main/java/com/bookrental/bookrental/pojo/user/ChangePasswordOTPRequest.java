package com.bookrental.bookrental.pojo.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordOTPRequest {
    private String email;
    private String newPassword;
}