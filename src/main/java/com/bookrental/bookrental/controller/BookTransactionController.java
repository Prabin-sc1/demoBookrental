package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.generic.GlobalApiResponse;
import com.bookrental.bookrental.model.BookTransaction;
import com.bookrental.bookrental.pojo.rent.BookRentRequest;
import com.bookrental.bookrental.pojo.returnn.BookReturnRequest;
import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;
import com.bookrental.bookrental.service.booktransaction.BookTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Rent transaction",
            description = "This end point used to create transaction for renting purpose.",
            responses = @ApiResponse(responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = BookRentRequest.class))
                    }
            )
    )
    public ResponseEntity<GlobalApiResponse> rentTransaction(@RequestBody @Valid BookRentRequest bookRentRequest) {
//        bookTransactionService.addBookTransaction(bookRentRequest);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.SAVE.getCode(), module),
                bookTransactionService.addBookTransaction(bookRentRequest)));
    }

    @PostMapping("/return")
    @Operation(
            summary = "Return Transaction",
            description = "This end point used to update transaction for returning book.",
            responses = @ApiResponse(responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = BookReturnRequest.class))
                    }
            )
    )
    public ResponseEntity<GlobalApiResponse> returnTransaction(@RequestBody BookReturnRequest bookRentRequest) {
        bookTransactionService.returnBookTransaction(bookRentRequest);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.UPDATE.getCode(), module), null));
    }

    @GetMapping
    @Operation(
            summary = "Retrieve all transaction",
            responses = {
                    @ApiResponse(responseCode = "200", content = {@Content
                            (array = @ArraySchema
                                    (schema = @Schema(implementation = BookTransactionResponse.class)))},
                            description = "This end point fetch all transaction"
                    )
            }
    )
    public ResponseEntity<GlobalApiResponse> getAllTransaction() {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIVE_ALL.getCode(), module),
                bookTransactionService.getAllTransaction()));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get transaction by id",
            description = "This end point can be used for getting transaction by id",
            responses = @ApiResponse(responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = BookTransactionResponse.class))
                    }
            )
    )
    public ResponseEntity<GlobalApiResponse> getSingleTransaction(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIEVE.getCode(), module), bookTransactionService.getSingleTransactionById(id)));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete transaction",
            description = "This end point is used to delete transaction",
            responses = @ApiResponse(responseCode = "200"
            )
    )
    public ResponseEntity<BookTransaction> delete(@PathVariable("id") Integer id) {
        bookTransactionService.deleteTransactionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/member/{id}")
    @Operation(
            summary = "Get transaction by member id",
            description = "This end point can be used for getting transaction member by id",
            responses = @ApiResponse(responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = BookTransactionResponse.class))
                    }
            )
    )
    public ResponseEntity<List<BookTransactionResponse>> getAllTransactionByMemberId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(bookTransactionService.getAllTransactionByMember(id));
    }
}
