package com.bookrental.bookrental.service.book;

import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.pojo.BookRequestPojo;

public interface BookService {
    void createBook(BookRequestPojo book);
}
