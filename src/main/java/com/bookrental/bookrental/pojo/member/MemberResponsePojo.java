package com.bookrental.bookrental.pojo.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponsePojo {
    private Integer id;
    private String email;
    private String name;
    private String mobileNumber;
    private String address;
}
