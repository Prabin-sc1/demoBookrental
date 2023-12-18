package com.bookrental.bookrental.repository;

import com.bookrental.bookrental.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
