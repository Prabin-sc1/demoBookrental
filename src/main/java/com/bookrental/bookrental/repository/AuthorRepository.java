package com.bookrental.bookrental.repository;

import com.bookrental.bookrental.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
