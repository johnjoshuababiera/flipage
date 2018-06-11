package com.cpu.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByIdNumber(String idNo);
    User findByEmail(String email);
    List<User> findByAdminIsTrue();
}