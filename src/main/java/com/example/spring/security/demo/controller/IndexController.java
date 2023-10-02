package com.example.spring.security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}
