package com.bookrental.bookrental.repository;

import com.bookrental.bookrental.model.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTransactionRepository extends JpaRepository<BookTransaction, Integer> {
}
