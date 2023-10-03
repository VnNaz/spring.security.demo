package com.example.spring.security.demo.service;

import com.example.spring.security.demo.domain.Role;
import com.example.spring.security.demo.domain.User;
import com.example.spring.security.demo.enums.Authority;
import com.example.spring.security.demo.model.WebUser;
import com.example.spring.security.demo.repository.RoleRepository;
import com.example.spring.security.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserDatabaseService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserDatabaseService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
    @Transactional
    public User saveUser(WebUser webUser){

        User user = new User();
        // set id = 0 to force insert
        user.setId(0);
        user.setUsername(webUser.getUsername());
        user.setPassword(passwordEncoder.encode(webUser.getPassword()));
        user.setFirstName(webUser.getFirstName());
        user.setLastName(webUser.getLastName());
        user.setEmail(webUser.getEmail());
        user.setEnabled(true);

        user.setRoles(Arrays.asList(roleRepository.findRoleByAuthority(Authority.EMPLOYEE)));

        User dbUser = userRepository.save(user);

        return dbUser;
    }
}
