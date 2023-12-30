package com.bookrental.bookrental.repository;

import com.bookrental.bookrental.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
