package com.bookrental.bookrental.service.booktransaction;

import com.bookrental.bookrental.pojo.rent.BookRentRequest;
import com.bookrental.bookrental.pojo.returnn.BookReturnRequest;
import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;

import java.util.List;

public interface BookTransactionService {
    void addBookTransaction(BookRentRequest bookRentRequest);

    void returnBookTransaction(BookReturnRequest bookReturnRequest);

    List<BookTransactionResponse> getAllTransaction();

    BookTransactionResponse getSingleTransactionById(Integer id);

    void deleteTransactionById(Integer id);

   List<BookTransactionResponse> getAllTransactionByMember(Integer id);
}
