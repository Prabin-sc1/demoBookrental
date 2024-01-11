package com.bookrental.bookrental.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequestPojo {
    private String to;
    private String subject;
    private String message;
}
