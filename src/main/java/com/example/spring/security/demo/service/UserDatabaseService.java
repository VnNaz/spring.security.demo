package com.example.spring.security.demo.service;

import com.example.spring.security.demo.DTO.UserMapper;
import com.example.spring.security.demo.domain.User;
import com.example.spring.security.demo.enums.Authority;
import com.example.spring.security.demo.DTO.UserDTO;
import com.example.spring.security.demo.repository.RoleRepository;
import com.example.spring.security.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserDatabaseService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    @Autowired
    public UserDatabaseService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
    @Transactional()
    public User saveUser(UserDTO dto){

        User user = userMapper.toEntity(dto);

        user.setRoles(Arrays.asList(roleRepository.findRoleByAuthority(Authority.EMPLOYEE)));

        User dbUser = userRepository.save(user);

        return dbUser;
    }
}
