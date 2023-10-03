package com.example.spring.security.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public UserDetailsManager userDetailsManager() {

        UserDetails nazvn = User.builder()
                .username("nazvn")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails vnnaz = User.builder()
                .username("vnnaz")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails vnam0320 = User.builder()
                .username("vnam0320")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(nazvn, vnnaz, vnam0320);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests( request -> request
                        // get request to home and index is free
                        .requestMatchers("/", "index").permitAll()
                        // but in other client must login
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        // if we want use default processing
                        // in input must use name=username, and name=password
                        .loginPage("/login")
                        // the default login processing, provided by Spring
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

}
