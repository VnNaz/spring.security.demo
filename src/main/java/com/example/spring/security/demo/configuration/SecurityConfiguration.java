package com.example.spring.security.demo.configuration;

import com.example.spring.security.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    // using jdbc security from now
//    @Bean
//    public UserDetailsManager userDetailsManager() {
//
//        UserDetails nazvn = User.builder()
//                .username("nazvn")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails vnnaz = User.builder()
//                .username("vnnaz")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails vnam0320 = User.builder()
//                .username("vnam0320")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(nazvn, vnnaz, vnam0320);
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests( request -> request
                        // get request to home and index is free
                        //.requestMatchers("/", "/index").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/manager/**").hasRole("MANAGER")
                        .requestMatchers("/meeting").authenticated()
                        //.requestMatchers("/signup").permitAll()
                        // but in other client must login
                        //.anyRequest().authenticated())
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        // if we want use default processing
                        // in input must use name=username, and name=password
                        .loginPage("/login")
                        // the default login processing, provided by Spring
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())
                .logout(logout -> logout
//                        .logoutSuccessUrl("/")
                        .permitAll())
                .exceptionHandling( configurer -> configurer
                        .accessDeniedPage("/access-denied"));

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService service)
    {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder());
        auth.setUserDetailsService(service);
        return auth;
    }
}
