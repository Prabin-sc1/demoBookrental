package com.bookrental.bookrental.pojo;

import lombok.Getter;

@Getter
public class VerifyOTPRequest {
    private String email;
    private int otp;
    private String newPassword;
}
