package com.bookrental.bookrental.pojo.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestPojo {
    private Integer id;
    private String name;
    private Integer noOfPages;
    private Integer isbn;
    private Double rating;
    private Integer stockCount;
    private LocalDate publishedDate;
    private String photo;
}
