package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.generic.GlobalApiResponse;
import com.bookrental.bookrental.model.BookTransaction;
import com.bookrental.bookrental.pojo.rent.BookRentRequest;
import com.bookrental.bookrental.pojo.returnn.BookReturnRequest;
import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;
import com.bookrental.bookrental.service.booktransaction.BookTransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booktransaction")
@Tag(name = ModuleNameConstants.TRANSACTION)
public class BookTransactionController extends MyBaseController {
    private final BookTransactionService bookTransactionService;

    public BookTransactionController(BookTransactionService bookTransactionService) {
        this.bookTransactionService = bookTransactionService;
        this.module = ModuleNameConstants.TRANSACTION;
    }

    @PostMapping("/rent")
    public ResponseEntity<GlobalApiResponse> createTransaction(@RequestBody @Valid BookRentRequest bookRentRequest) {
        bookTransactionService.addBookTransaction(bookRentRequest);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.SAVE.getCode(), module), null));
    }

    @PostMapping("/return")
    public ResponseEntity<GlobalApiResponse> returnTransaction(@RequestBody BookReturnRequest bookRentRequest) {
        bookTransactionService.returnBookTransaction(bookRentRequest);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.UPDATE.getCode(), module), null));
    }

    @GetMapping
    public ResponseEntity<GlobalApiResponse> getAllTransaction() {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIVE_ALL.getCode(), module), bookTransactionService.getAllTransaction()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> getSingleTransaction(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIEVE.getCode(), module), bookTransactionService.getSingleTransactionById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookTransaction> delete(@PathVariable("id") Integer id) {
        bookTransactionService.deleteTransactionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<List<BookTransactionResponse>> getAllTransactionByMemberId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(bookTransactionService.getAllTransactionByMember(id));
    }
}
