package com.codegym.validate_form_register.controller;

import com.codegym.validate_form_register.model.User;
import com.codegym.validate_form_register.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    private final UserService userService;

    public FormController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/")
    public String register(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        User savedUser = userService.save(user);
        model.addAttribute("user", savedUser);
        return "result";
    }
}
