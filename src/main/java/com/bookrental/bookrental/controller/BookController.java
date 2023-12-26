package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.pojo.book.BookRequestPojo;
import com.bookrental.bookrental.pojo.book.BookResponsePojo;
import com.bookrental.bookrental.service.book.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookRequestPojo> create(@Valid @RequestBody BookRequestPojo bookRequestPojo) {
        this.bookService.createUpdateBook(bookRequestPojo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookRequestPojo> delete(@PathVariable Integer id) {
        this.bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponsePojo> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.bookService.getBookById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookResponsePojo>> getAll() {
        return ResponseEntity.ok(this.bookService.getAllBooks());
    }

}
