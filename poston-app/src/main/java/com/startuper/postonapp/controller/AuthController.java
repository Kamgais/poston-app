package com.startuper.postonapp.controller;


import com.startuper.postonapp.dtos.UserDto;
import com.startuper.postonapp.entities.User;
import com.startuper.postonapp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor

@RequestMapping("/api/auth")
public class AuthController {

   private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> createAccount(@RequestBody UserDto userDto) {

     return authService.createAUser(userDto);
    }


    @PostMapping("/login")
    public ResponseEntity<UserDto> verifyAUser(@RequestBody UserDto userDto) {
        return authService.loginAUser(userDto);
    }
}