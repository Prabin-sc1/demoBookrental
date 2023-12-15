package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.service.book.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
private BookService bookService;


}
