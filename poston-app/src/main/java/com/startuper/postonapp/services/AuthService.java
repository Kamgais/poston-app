package com.startuper.postonapp.services;


import com.startuper.postonapp.dtos.UserDto;
import com.startuper.postonapp.entities.User;
import com.startuper.postonapp.mappers.UserMapper;
import com.startuper.postonapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
  private  IUserRepository userRepository;

    @Autowired
    private UserMapper userMapper;




    /*
    * create a account
     */
    public UserDto createAUser(UserDto userDto) {

        User newUser = userMapper.toEntity(userDto);
        if (emailExist(userDto.getEmailAddress())) {
            // TODO : errors handling
            return null;
        }

        if(userDto.getPassword().length() < 3) {
            // TODO : errors handling
           return null;
        }

        try {
            userRepository.save(newUser);

        } catch (Error error) {
            System.out.println(error);
        }
        return  userMapper.toDto(newUser);
    }

    /*
    * exist email
     */
    public boolean emailExist(String email) {
        Optional<User> user = userRepository.findUserByEmailAddress(email);
        if(user.isPresent()) {
            return true;
        } else {
            return false;
        }

    }

    /*
    * login email
     */
    public UserDto loginAUser(UserDto userDto) {
        User fetchUser = userRepository.findUserByEmailAddress(userDto.getEmailAddress()).get();
        if(fetchUser == null) {
            // TODO : errors handling
            return null;
        } else if(!fetchUser.getPassword().equals(userDto.getPassword())) {
            // TODO : errors handling
          return null;
        } else {
            UserDto loggedUser = userMapper.toDto(fetchUser);
            return loggedUser;
        }
    }





}
