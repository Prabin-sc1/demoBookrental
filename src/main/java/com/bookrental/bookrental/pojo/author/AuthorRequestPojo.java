package com.bookrental.bookrental.pojo.author;

import com.bookrental.bookrental.customvalidation.Gmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorRequestPojo {
    private Integer id;
    @NotBlank
    private String name;
    @Gmail(gmailOnly = true)
    private String email;
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid mobile number")
    private String mobileNumber;
}
