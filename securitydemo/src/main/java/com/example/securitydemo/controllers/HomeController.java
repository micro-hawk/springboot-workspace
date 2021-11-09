package com.example.securitydemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/p")
public class HomeController {

    @GetMapping(value = "/")
    public String home() {
        return "Hello World!";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "Login Page";
    }

    @GetMapping(value = "/register")
    public String register(){
        return "Register Page";
    }


}
