package com.bookrental.bookrental.pojo.returnn;

import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class BookReturnResponse {
    private Member member;
    private Book book;
}
