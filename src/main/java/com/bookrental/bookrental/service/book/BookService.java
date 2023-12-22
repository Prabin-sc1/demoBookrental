package com.bookrental.bookrental.service.book;

import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.pojo.book.BookRequestPojo;
import com.bookrental.bookrental.pojo.book.BookResponsePojo;

import java.util.List;

public interface BookService {
    void createUpdateBook(BookRequestPojo book);

    void deleteBook(Integer id);

    List<BookResponsePojo> getAllBook();

    BookResponsePojo getBook(Integer id);

}
