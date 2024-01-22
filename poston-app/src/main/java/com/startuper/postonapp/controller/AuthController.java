package com.startuper.postonapp.controller;


import com.startuper.postonapp.dtos.UserDto;
import com.startuper.postonapp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequiredArgsConstructor

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class AuthController {

   private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> createAccount(@RequestBody UserDto userDto) {

     return ResponseEntity.ok().body(authService.createAUser(userDto));
    }


    @PostMapping("/login")
    public ResponseEntity<UserDto> verifyAUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(authService.loginAUser(userDto));
    }
}
