package com.example.postonapp.mappers;


import com.example.postonapp.dtos.UserDto;
import com.example.postonapp.entities.User;



public class UserMapper implements Mapper<User,UserDto> {

    @Override
    public  User toEntity(UserDto userDto) {
        if(userDto == null) {
            return null;
        }
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .emailAddress(userDto.getEmailAddress())
                .password(userDto.getPassword())
                .build();
    }





    @Override
    public  UserDto toDto(User user) {
        if(user == null ) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .emailAddress(user.getEmailAddress())
                .build();
    }
}
