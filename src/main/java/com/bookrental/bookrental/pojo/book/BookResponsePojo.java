package com.bookrental.bookrental.pojo.book;

import com.bookrental.bookrental.model.Author;
import com.bookrental.bookrental.pojo.author.AuthorResponsePojo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class BookResponsePojo {
    private Integer id;
    private String name;
    private Integer noOfPages;
    private Long isbn;
    private Double rating;
    private Integer stockCount;
    private LocalDate publishedDate;
    private String photo;
    private Integer categoryId;
    private List<Author> authors;
}
