package com.bookrental.bookrental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class OTPToken {
    @Id
    private String email;
    private int otp;
    private LocalDateTime expirationTime;
}
