package com.example.spring.security.demo.service;

import com.example.spring.security.demo.domain.Role;
import com.example.spring.security.demo.domain.User;
import com.example.spring.security.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("username is not found");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> authorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority().getValue())).collect(Collectors.toList());
    }
}
