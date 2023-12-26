package com.bookrental.bookrental.service.booktransaction;

import com.bookrental.bookrental.model.BookTransaction;
import com.bookrental.bookrental.pojo.rent.BookRentRequest;
import com.bookrental.bookrental.pojo.rent.BookRentResponse;
import com.bookrental.bookrental.pojo.returnn.BookReturnRequest;
import com.bookrental.bookrental.pojo.returnn.BookReturnResponse;
import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;

import java.util.List;

public interface BookTransactionService {
    void addBookTransaction(BookRentRequest bookRentRequest);

    BookReturnResponse returnBookTransaction(BookReturnRequest bookReturnRequest);

    List<BookTransactionResponse> getAllTransaction();
}
