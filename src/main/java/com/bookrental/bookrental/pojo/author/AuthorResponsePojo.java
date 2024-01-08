package com.bookrental.bookrental.pojo.author;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AuthorResponsePojo {
    private Integer id;
    private String name;
    private String email;
    private String mobileNumber;
}
