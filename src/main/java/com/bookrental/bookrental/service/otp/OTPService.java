package com.bookrental.bookrental.service.otp;

public interface OTPService {
    int generateAndStore(String email);

    boolean verifyOTP(String email, int userEnteredOTP, String newPassword);
}
