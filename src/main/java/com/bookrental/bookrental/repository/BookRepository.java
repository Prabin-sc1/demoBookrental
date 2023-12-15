package com.bookrental.bookrental.repository;

import com.bookrental.bookrental.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
