package com.bookrental.bookrental.service.author;

import com.bookrental.bookrental.model.Author;
import com.bookrental.bookrental.pojo.author.AuthorRequestPojo;

import java.util.List;

public interface AuthorService {

    void createUpdateAuthor(AuthorRequestPojo authorRequestPojo);

    List<Author> getAllAuthor();

    Author getAuthorById(Integer id);

    void deleteById(Integer id);
}
