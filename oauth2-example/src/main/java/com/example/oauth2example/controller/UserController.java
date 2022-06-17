package com.example.oauth2example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/login")
    public String loginpage(){
        return "login";
    }

    @GetMapping("/list")
    public String getlist(){
        return "list";
    }
}
