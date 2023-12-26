package com.bookrental.bookrental.pojo.returnn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookReturnRequest {
    private Integer id;
    private String code;
    private Integer memberId;
}
