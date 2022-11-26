package com.example.postonapp.services;


import com.example.postonapp.dtos.UserDto;
import com.example.postonapp.entities.*;
import com.example.postonapp.mappers.UserMapper;
import com.example.postonapp.repositories.*;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    IPostRepository postRepository;
    @Autowired
    PostService postService;
    @Autowired
    ICommentRepository commentRepository;
    @Autowired
    INotificationRepository notificationRepository;

    @Autowired
    ITagRepository tagRepository;
    @Autowired
    IImageRepository imageRepository;

    @Autowired
    UserMapper userMapper;

    public ResponseEntity<List<UserDto>> getUsersByUsername(String username) {
     List<User> users = userRepository.findUsersByUsername(username);

     return ResponseEntity.ok().body(users.stream().limit(5).map(e-> userMapper.toDto(e)).collect(Collectors.toList()));
    }


    public ResponseEntity<UserDto> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.ok().body(userMapper.toDto(user.get()));
    }

    public ResponseEntity<UserDto> updateUser(Long id, UserDto updatedUser) {
        Optional<User> dbUser = userRepository.findById(id);
        User user = userMapper.toEntity(updatedUser);
        user.setPassword(dbUser.get().getPassword());
        user.setId(id);
        User newUser = userRepository.save(user);


        return ResponseEntity.ok().body(userMapper.toDto(newUser));


    }


    public ResponseEntity<String> deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user == null) {
            return ResponseEntity.badRequest().build();
        } else  {
           List<Post> posts =  postRepository.findPostsByUserId(id);
           List<Notification> notifs = notificationRepository.findAllByUserId(id);
           List<Comment> comments = commentRepository.findCommentsByUserId(id);
           List<Tag> tags = tagRepository.findTagsByUserId(id);
           posts.stream().forEach(e-> postService.deletePost(e.getId()));
           notificationRepository.deleteAll(notifs);
           commentRepository.deleteAll(comments);
           tagRepository.deleteAll(tags);
            Image image = imageRepository.findImageByName(user.get().getImage().getName());
           userRepository.deleteById(id);
           imageRepository.deleteById(image.getId());
            return ResponseEntity.ok().body("account deleted");
        }
    }
}
