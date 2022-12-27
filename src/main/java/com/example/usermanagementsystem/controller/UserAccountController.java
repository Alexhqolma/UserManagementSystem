package com.example.usermanagementsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/users")
public class UserAccountController {
    @GetMapping
    public ModelAndView userMain() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/users");
        return modelAndView;
    }
}
