package com.bookrental.bookrental.service.book;

import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.pojo.book.BookRequestPojo;

import java.util.List;

public interface BookService {
    void createUpdateBook(BookRequestPojo book, Integer cid);

    void deleteBook(Integer id);

    List<Book> getAllBook();

    Book getBook(Integer id);

    List<Book> getAllBookByCategory(Integer categoryId);

    List<Book> getAllBookByAuthor(Integer authorId);

}
