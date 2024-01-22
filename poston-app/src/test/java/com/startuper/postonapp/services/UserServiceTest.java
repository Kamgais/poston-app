package com.startuper.postonapp.services;

import com.startuper.postonapp.dtos.UserDto;
import com.startuper.postonapp.entities.*;
import com.startuper.postonapp.mappers.UserMapper;
import com.startuper.postonapp.repositories.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IPostRepository postRepository;

    @Mock
    private ICommentRepository commentRepository;

    @Mock
    private ITagRepository tagRepository;



    @Mock
    private INotificationRepository notificationRepository;

    @Mock
    private PostService postService;

    @Mock
    private UserMapper mapper;

    @Test
    public void UserService_GetByID_ReturnsUserDTO() {
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

        when(userRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(user));
        when(mapper.toDto(Mockito.any(User.class))).thenReturn(userDto);
        UserDto foundUser = userService.getUserById(Mockito.any(Long.class));

        Assertions.assertThat(foundUser).isNotNull();
    }


    @Test
    public void UserService_UpdateUser_ReturnsUserDTO() {
        User user = User.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();
        User user2 = User.builder()
                .username("postonuser2")
                .emailAddress("postonuser2@startuper.com")
                .password("poston1!")
                .build();
        UserDto userDto = UserDto.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();

        UserDto userDto2 = UserDto.builder()
                .username("postonuser2")
                .emailAddress("postonuser2@startuper.com")
                .password("poston1!")
                .build();
        when(userRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(user));
        when(mapper.toDto(Mockito.any(User.class))).thenReturn(userDto2);
        when(mapper.toEntity(Mockito.any(UserDto.class))).thenReturn(user);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user2);

        UserDto updatedUser = userService.updateUser(Mockito.any(Long.class), userDto2);
        Assertions.assertThat(updatedUser).isNotNull();
        Assertions.assertThat(updatedUser.getUsername()).isEqualTo("postonuser2");
        Assertions.assertThat(updatedUser.getEmailAddress()).isEqualTo("postonuser2@startuper.com");


    }


    @Test
    public void UserService_DeleteUser_ReturnsUserDTO() {

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

        List<Post> postList = Mockito.mock(List.class);
        List<Notification> notificationList = Mockito.mock(List.class);
        List<Tag> tagList = Mockito.mock(List.class);
        List<Comment> commentList = Mockito.mock(List.class);


        when(userRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(user));
        when(postRepository.findPostsByUserId(Mockito.any(Long.class))).thenReturn(postList);
        when(notificationRepository.findAllByUserId(Mockito.any(Long.class))).thenReturn(notificationList);
        when(tagRepository.findTagsByUserId(Mockito.any(Long.class))).thenReturn(tagList);
        when(commentRepository.findCommentsByUserId(Mockito.any(Long.class))).thenReturn(commentList);
        Mockito.doNothing().when(postRepository).deleteAll(Mockito.any());
        Mockito.doNothing().when(notificationRepository).deleteAll(Mockito.any());
        Mockito.doNothing().when(commentRepository).deleteAll(Mockito.any());
        Mockito.doNothing().when(tagRepository).deleteAll(Mockito.any());
        Mockito.doNothing().when(userRepository).deleteById(Mockito.anyLong());
    //    Mockito.doNothing().when(imageRepository).deleteById(Mockito.anyLong());

        // Act
        String confirmDeletion = userService.deleteUser(1L);

        // Assertions
        Assertions.assertThat(confirmDeletion).isNotNull();
        Assertions.assertThat(confirmDeletion).isEqualTo("account deleted");


    }

}
