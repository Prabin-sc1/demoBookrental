package com.bookrental.bookrental.pojo.book;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class BookRequestPojo {

    private Integer id;
    @NotBlank(message = "Book name cannot be blank")
    @Size(min = 2, message = "Book name must have at least 2 characters")
    private String name;
    @NotNull(message = "Number of pages cannot be blank")
    @Min(value = 1, message = "Number of pages must be greater than 0")
    private Integer noOfPages;
    @NotNull(message = "ISBN cannot be blank")
    @Min(value = 1000000000000L, message = "ISBN must be a 13-digit number")
    @Max(value = 9999999999999L, message = "ISBN must be a 13-digit number")
    private Long isbn;
    @NotNull(message = "Rating cannot be blank")
    @Min(value = 0, message = "Rating must be between 0 and 5")
    @Max(value = 5, message = "Rating must be between 0 and 5")
    private Double rating;
    @NotNull(message = "Stock count cannot be blank")
    @Min(value = 0, message = "Stock count must be non-negative")
    private Integer stockCount;
    @NotNull(message = "Published date cannot be blank")
    @Past(message = "Published date must be in the past")
    private LocalDate publishedDate;
//    @NotBlank(message = "Photo URL cannot be blank")
//    private String photo;

    @NotNull(message = "Photo file cannot be blank")
    private MultipartFile photo;

    @NotNull(message = "Category id can't be null")
    private Integer categoryId;
    @NotNull(message = "At least one author id is required")
    private List<Integer> authorId;
}
