package com.bookrental.bookrental.pojo.member;

import com.bookrental.bookrental.customvalidation.Gmail;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestPojo {
    private Integer id;
    @Gmail(gmailOnly = true)
    private String email;
    @NotNull
    private String name;

    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid mobile number")
    private String mobileNumber;

    @NotNull
    private String address;
}
