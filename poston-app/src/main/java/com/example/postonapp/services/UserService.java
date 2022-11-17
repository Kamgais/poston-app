package com.example.postonapp.services;


import com.example.postonapp.dtos.UserDto;
import com.example.postonapp.entities.User;
import com.example.postonapp.mappers.UserMapper;
import com.example.postonapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public ResponseEntity<List<UserDto>> getUsersByUsername(String username) {
     List<User> users = userRepository.findUsersByUsername(username);

     return ResponseEntity.ok().body(users.stream().limit(5).map(e-> userMapper.toDto(e)).collect(Collectors.toList()));
    }
}
