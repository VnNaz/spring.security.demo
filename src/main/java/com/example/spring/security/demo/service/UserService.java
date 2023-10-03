package com.example.spring.security.demo.service;

import com.example.spring.security.demo.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUsername(String username);
}
