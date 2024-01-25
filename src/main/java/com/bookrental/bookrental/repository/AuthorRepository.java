package com.bookrental.bookrental.repository;

import com.bookrental.bookrental.model.Author;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Modifying
    @Query(value = "update tbl_author set is_active = false where id = :id", nativeQuery = true)
    void deleteById(@Param("id") Integer id);


    @Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'tbl_author'", nativeQuery = true)
    String[] getColumnNames();
}
