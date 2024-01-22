package com.bookrental.bookrental.pojo.member;

import com.bookrental.bookrental.customvalidation.Gmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestPojo {
    private Integer id;
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "please enter valid email")
    private String email;
    @NotBlank
    private String name;

    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid mobile number")
    private String mobileNumber;

    @NotBlank
    private String address;
}
