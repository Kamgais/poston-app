package com.example.postonapp.controller;


import com.example.postonapp.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {



    @GetMapping("register")
    public User createAccount(User user) {
     return user;
    }
}
