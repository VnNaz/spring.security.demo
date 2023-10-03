package com.example.spring.security.demo.controller;

import com.example.spring.security.demo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IndexController {
    @GetMapping(value = {"/", "index"})
    public String getIndex(){
        return "index";
    }
    @GetMapping("/meeting")
    public String getMeeting(){
        return "meeting";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
    @GetMapping("/admin")
    public String getAdmin(){
        return "admin";
    }
    @GetMapping("/manager")
    public String getManager(){
        return "manager";
    }
    @GetMapping("/error")
    public String getError(){
        return "error";
    }
    @GetMapping("/access-denied")
    public String getAccessDenied(){
        return "access-denied";
    }
    @GetMapping("/signup")
    public String getSignup(@ModelAttribute("user") User user){
        return "signup";
    }
}
