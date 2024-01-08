package com.bookrental.bookrental.pojo.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class BookRequestPojo {

    private Integer id;
    @NotBlank
    @Size(min = 2, message = "minimum 2 characters")
    private String name;
    @NotNull
    private Integer noOfPages;
    @NotNull
    private Integer isbn;
    @NotNull
    private Double rating;
    @NotNull
    private Integer stockCount;
    @NotNull
    private LocalDate publishedDate;
    @NotBlank
    private String photo;
    @NotNull
    private Integer categoryId;
    @NotNull
    private List<Integer> authorId;
}
