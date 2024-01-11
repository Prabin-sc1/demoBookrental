package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.generic.GlobalApiResponse;
import com.bookrental.bookrental.pojo.book.BookRequestPojo;
import com.bookrental.bookrental.service.book.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController extends MyBaseController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
        this.module = ModuleNameConstants.BOOK;
    }

    @PostMapping
    public ResponseEntity<GlobalApiResponse> create(@Valid @RequestBody BookRequestPojo bookRequestPojo) {
        bookService.createUpdateBook(bookRequestPojo);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.SAVE.getCode(), module),
                null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIEVE.getCode(), module), bookService.getBookById(id)));
    }

    @GetMapping
    public ResponseEntity<GlobalApiResponse> getAll() {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIVE_ALL.getCode(), module), bookService.getAllBooks()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> delete(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.DELETE.getCode(), module), null));
    }
}
