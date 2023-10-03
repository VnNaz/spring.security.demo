package com.example.spring.security.demo.controller;

import com.example.spring.security.demo.domain.Role;
import com.example.spring.security.demo.domain.User;
import com.example.spring.security.demo.model.WebUser;
import com.example.spring.security.demo.repository.UserRepository;
import com.example.spring.security.demo.service.UserDatabaseService;
import com.example.spring.security.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Controller
public class UserController {

    private UserDatabaseService userDatabaseService;
    @Autowired
    public UserController(UserDatabaseService userDatabaseService) {
        this.userDatabaseService = userDatabaseService;
    }

    @PostMapping("/signupProcess")
    public String signupProcess(@Valid @ModelAttribute("user") WebUser user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        if(bindingResult.hasErrors())
            return "signup";
        if(userDatabaseService.findUserByUsername(user.getUsername()) != null)
        {
            // clear current webuser
            model.addAttribute("user", new WebUser());

            // add error message
            model.addAttribute("errormess", "current username already taken");
            return "signup";
        }
        userDatabaseService.saveUser(user);

        return "redirect:/index";
    }
}
