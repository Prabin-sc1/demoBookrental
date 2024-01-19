package com.bookrental.bookrental.repository;

import com.bookrental.bookrental.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Query(value = "update tbl_user set is_active = false where id = :id", nativeQuery = true)
    void deleteById(@Param("id") Integer id);
}
