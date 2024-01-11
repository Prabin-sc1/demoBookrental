package com.bookrental.bookrental.pojo.user;

import com.bookrental.bookrental.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestPojo {
    private Integer id;
    private String email;
    private String password;
    private Role role;
}
