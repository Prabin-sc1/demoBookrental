package com.bookrental.bookrental.pojo.rent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRentRequest {
    private Integer id;
    private String code;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Boolean activeClosed;
    private Integer bookId;
    private Integer memberId;
}
