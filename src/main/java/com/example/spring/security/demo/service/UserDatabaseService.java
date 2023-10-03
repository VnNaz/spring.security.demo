package com.example.spring.security.demo.service;

import com.example.spring.security.demo.domain.Role;
import com.example.spring.security.demo.domain.User;
import com.example.spring.security.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserDatabaseService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserDatabaseService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User saveUser(User user){

        user.setId(0);
        user.setPassword(passwordEncoder.encode(user.password));
        user.setEnabled(true);
        user.setRoles(Arrays.asList(new Role("EMPLOYEE")));

        User dbUser = userRepository.save(user);

        return dbUser;
    }
}
