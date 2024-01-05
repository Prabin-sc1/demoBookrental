package com.bookrental.bookrental.repository;

import com.bookrental.bookrental.model.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Modifying
    @Query(value = "update tbl_category set is_active = false where id = :id", nativeQuery = true)
    void deleteById(@Param("id") Integer id);
}
