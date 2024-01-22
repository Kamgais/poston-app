package com.startuper.postonapp.services;


import com.startuper.postonapp.dtos.UserDto;
import com.startuper.postonapp.entities.User;
import com.startuper.postonapp.mappers.UserMapper;
import com.startuper.postonapp.repositories.IUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;


import javax.swing.text.html.Option;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private AuthService authService;

    @InjectMocks
    private UserService userService;


    @Test
    public void UserService_CreateUser_ReturnsUserDTO() {

        User user = User.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();
        UserDto userDto = UserDto.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        when(userRepository.findUserByEmailAddress(Mockito.any(String.class))).thenReturn(Optional.empty());
        when(mapper.toEntity(Mockito.any(UserDto.class))).thenReturn(user);
        when(mapper.toDto(Mockito.any(User.class))).thenReturn(userDto);
        UserDto savedUser = authService.createAUser(userDto);
        Assertions.assertThat(savedUser).isNotNull();

    }


    @Test
    public void UserService_Login_ReturnsUserDTO() {

        User user = User.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();
        UserDto userDto = UserDto.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();

        Optional<User> op = Optional.of(user);
        when(userRepository.findUserByEmailAddress(Mockito.any(String.class))).thenReturn(op);
        when(mapper.toDto(Mockito.any(User.class))).thenReturn(userDto);
        UserDto loggedUser = authService.loginAUser(userDto);
        Assertions.assertThat(loggedUser).isNotNull();
    }
}
