package com.startuper.postonapp.mappers;


import com.startuper.postonapp.dtos.UserDto;
import com.startuper.postonapp.entities.User;
import org.springframework.stereotype.Service;


@Service
public class UserMapper implements Mapper<User,UserDto> {






    @Override
    public  User toEntity(UserDto userDto) {
        if(userDto == null) {
            return null;
        }

        if( userDto.getProfilePic() == null) {
            return User.builder()
                    .id(userDto.getId())
                    .username(userDto.getUsername())
                    .emailAddress(userDto.getEmailAddress())
                    .password(userDto.getPassword())
                    .build();
        } else {
            return User.builder()
                    .id(userDto.getId())
                    .username(userDto.getUsername())
                    .emailAddress(userDto.getEmailAddress())
                    .password(userDto.getPassword())
                    .profilePic(userDto.getProfilePic())
                    .build();
        }

    }





    @Override
    public  UserDto toDto(User user) {
        if(user == null ) {
            return null;
        }

        if(user.getProfilePic() == null) {
            return UserDto.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .emailAddress(user.getEmailAddress())
                    .build();
        }  else {
            return UserDto.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .emailAddress(user.getEmailAddress())
                    .profilePic(user.getProfilePic())
                    .build();
        }


    }
}
