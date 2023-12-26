package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.model.BookTransaction;
import com.bookrental.bookrental.pojo.rent.BookRentRequest;
import com.bookrental.bookrental.service.booktransaction.BookTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booktransaction")
public class BookTransactionController {
    private final BookTransactionService bookTransactionService;

    public BookTransactionController(BookTransactionService bookTransactionService) {
        this.bookTransactionService = bookTransactionService;
    }

    @PostMapping
    public ResponseEntity<BookTransaction> createTransaction(@RequestBody BookRentRequest bookRentRequest) {
        bookTransactionService.addBookTransaction(bookRentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
