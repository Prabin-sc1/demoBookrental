package com.bookrental.bookrental.pojo.returnn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BookReturnRequest {
    private Integer id;
    private String code;
    private Integer memberId;
    private Integer bookId;
    private LocalDate toDate;
}
