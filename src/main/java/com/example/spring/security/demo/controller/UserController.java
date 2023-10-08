package com.example.spring.security.demo.controller;

import com.example.spring.security.demo.DTO.UserDTO;
import com.example.spring.security.demo.service.UserDatabaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private UserDatabaseService userDatabaseService;
    @Autowired
    public UserController(UserDatabaseService userDatabaseService) {
        this.userDatabaseService = userDatabaseService;
    }

    @PostMapping("/signupProcess")
    public String signupProcess(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        if(bindingResult.hasErrors())
            return "signup";
        if(userDatabaseService.findUserByUsername(user.getUsername()) != null)
        {
            // clear current webuser
            model.addAttribute("user", new UserDTO());

            // add error message
            model.addAttribute("errormess", "current username already taken");
            return "signup";
        }
        userDatabaseService.saveUser(user);

        return "redirect:/index";
    }
}
