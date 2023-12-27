package com.bookrental.bookrental.pojo.trasaction;

import com.bookrental.bookrental.enums.RentType;
import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.model.Member;
import com.bookrental.bookrental.pojo.book.BookResponsePojo;
import com.bookrental.bookrental.pojo.member.MemberResponsePojo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookTransactionResponse {
    private Integer id;
    private String code;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer bookId;
    private Integer memberId;
    @Enumerated(EnumType.STRING)
    private RentType rentStatus;
}
