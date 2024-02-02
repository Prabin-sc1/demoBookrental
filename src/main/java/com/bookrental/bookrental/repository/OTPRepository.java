package com.bookrental.bookrental.repository;

import com.bookrental.bookrental.model.OTPToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPRepository extends JpaRepository<OTPToken, String> {
}
