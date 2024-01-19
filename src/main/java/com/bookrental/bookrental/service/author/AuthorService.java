package com.bookrental.bookrental.service.author;

import com.bookrental.bookrental.model.Author;
import com.bookrental.bookrental.pojo.author.AuthorRequestPojo;
import com.bookrental.bookrental.pojo.author.AuthorResponsePojo;

import java.util.List;

public interface AuthorService {
    void createUpdateAuthor(AuthorRequestPojo authorRequestPojo);

    List<AuthorResponsePojo> getAllAuthor();

    AuthorResponsePojo getAuthorById(Integer id);

    void deleteById(Integer id);

    Author findAuthorById(Integer id);

}
