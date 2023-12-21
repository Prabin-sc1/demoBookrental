package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.pojo.book.BookRequestPojo;
import com.bookrental.bookrental.service.book.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/category/{categoryId}/book")
    public ResponseEntity<Void> create(@Valid @RequestBody BookRequestPojo bookRequestPojo, @PathVariable Integer categoryId) {
        this.bookService.createUpdateBook(bookRequestPojo, categoryId);
        return null;
    }
}
