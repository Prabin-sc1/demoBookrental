package com.bookrental.bookrental.repository;

import com.bookrental.bookrental.model.Author;
import com.bookrental.bookrental.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
