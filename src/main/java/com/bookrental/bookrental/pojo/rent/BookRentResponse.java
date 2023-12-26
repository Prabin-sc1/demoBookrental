package com.bookrental.bookrental.pojo.rent;

import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRentResponse {
    private String code;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Member member;
    private Book book;
}
