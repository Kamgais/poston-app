package com.example.postonapp.controller;


import com.example.postonapp.dtos.UserDto;
import com.example.postonapp.entities.User;
import com.example.postonapp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




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
