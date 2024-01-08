package com.bookrental.bookrental.pojo.trasaction;

import com.bookrental.bookrental.enums.RentType;
import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.model.Member;
import com.bookrental.bookrental.pojo.book.BookResponsePojo;
import com.bookrental.bookrental.pojo.member.MemberResponsePojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookTransactionResponse {
    private Integer id;
    private String code;
    private LocalDate fromDate;
    private LocalDate toDate;
    @JsonIgnore
    private Integer bookId;
    @JsonIgnore
    private Integer memberId;
    private String bookName;
    private String memberName;
    @Enumerated(EnumType.STRING)
    private RentType rentStatus;
}
