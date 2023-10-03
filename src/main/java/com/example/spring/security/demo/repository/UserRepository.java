package com.example.spring.security.demo.repository;

import com.example.spring.security.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findUserByUsername(String username);
}
