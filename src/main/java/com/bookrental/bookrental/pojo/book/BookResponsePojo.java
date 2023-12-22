package com.bookrental.bookrental.pojo.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponsePojo {
    private Integer id;
    private String name;
    private Integer noOfPages;
    private Integer isbn;
    private Double rating;
    private Integer stockCount;
    private LocalDate publishedDate;
    private String photo;
    private Integer categoryId;
    private List<Integer> authorId;
}
