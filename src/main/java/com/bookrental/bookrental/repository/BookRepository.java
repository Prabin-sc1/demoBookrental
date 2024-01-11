package com.bookrental.bookrental.repository;

import com.bookrental.bookrental.model.Author;
import com.bookrental.bookrental.model.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Modifying
    @Query(value = "update tbl_book set is_active = false where id = :id", nativeQuery = true)
    void deleteById(@Param("id") Integer id);
}
