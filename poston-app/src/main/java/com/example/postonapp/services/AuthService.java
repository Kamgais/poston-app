package com.example.postonapp.services;


import com.example.postonapp.dtos.UserDto;
import com.example.postonapp.entities.User;
import com.example.postonapp.mappers.UserMapper;
import com.example.postonapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
  private  IUserRepository userRepository;

    UserMapper userMapper = new UserMapper();




    /*
    * create a account
     */
    public ResponseEntity<UserDto> createAUser(UserDto userDto) {

        User newUser = userMapper.toEntity(userDto);
        if (emailExist(userDto.getEmailAddress())) {
            return ResponseEntity.badRequest().build();
        }

        if(userDto.getPassword().length() < 6) {
           return ResponseEntity.badRequest().build();
        }

        try {
            userRepository.save(newUser);

        } catch (Error error) {
            System.out.println(error);
        }
        return  ResponseEntity.ok().body(userMapper.toDto(newUser));
    }

    /*
    * exist email
     */
    public boolean emailExist(String email) {
        User user = userRepository.findUserByEmailAddress(email);
        if(user != null) {
            return true;
        } else {
            return false;
        }

    }

    /*
    * login email
     */
    public ResponseEntity<UserDto> loginAUser(UserDto userDto) {
        User fetchUser = userRepository.findUserByEmailAddress(userDto.getEmailAddress());
        if(fetchUser == null) {
            return ResponseEntity.badRequest().build();
        } else if(!fetchUser.getPassword().equals(userDto.getPassword())) {
          return ResponseEntity.badRequest().build();
        } else {
            UserDto loggedUser = userMapper.toDto(fetchUser);
            return ResponseEntity.ok().body(loggedUser);
        }
    }





}
