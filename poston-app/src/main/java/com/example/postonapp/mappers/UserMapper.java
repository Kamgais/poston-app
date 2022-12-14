package com.example.postonapp.mappers;


import com.example.postonapp.dtos.UserDto;
import com.example.postonapp.entities.Image;
import com.example.postonapp.entities.User;
import com.example.postonapp.repositories.IImageRepository;
import com.example.postonapp.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserMapper implements Mapper<User,UserDto> {


    @Autowired
    private IImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private ImageService imageService;
    @Override
    public  User toEntity(UserDto userDto) {
        if(userDto == null) {
            return null;
        }

        if( userDto.getImage() == null) {
            return User.builder()
                    .id(userDto.getId())
                    .username(userDto.getUsername())
                    .emailAddress(userDto.getEmailAddress())
                    .password(userDto.getPassword())
                    .build();
        } else {
            Image image = imageRepository.findById(userDto.getImage().getId()).get();
            return User.builder()
                    .id(userDto.getId())
                    .username(userDto.getUsername())
                    .emailAddress(userDto.getEmailAddress())
                    .password(userDto.getPassword())
                    .image(image)
                    .build();
        }

    }





    @Override
    public  UserDto toDto(User user) {
        if(user == null ) {
            return null;
        }

        if(user.getImage() == null) {
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
                    .image(imageService.getImage(user.getImage().getName()))
                    .build();
        }


    }
}
