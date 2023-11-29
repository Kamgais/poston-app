package com.startuper.postonapp.services;


import com.startuper.postonapp.dtos.UserDto;
import com.startuper.postonapp.entities.*;
import com.startuper.postonapp.mappers.UserMapper;
import com.startuper.postonapp.repositories.*;
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


    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return (userMapper.toDto(user.get()));
    }

    public UserDto updateUser(Long id, UserDto updatedUser) {
        Optional<User> dbUser = userRepository.findById(id);
        User user = userMapper.toEntity(updatedUser);
        user.setPassword(dbUser.get().getPassword());
        user.setId(id);
        User newUser = userRepository.save(user);


        return userMapper.toDto(newUser);


    }


    public String deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user == null) {
            // TODO : errors handling
            return null;
        } else  {
           List<Post> posts =  postRepository.findPostsByUserId(id);
           List<Notification> notifs = notificationRepository.findAllByUserId(id);
           List<Comment> comments = commentRepository.findCommentsByUserId(id);
           List<Tag> tags = tagRepository.findTagsByUserId(id);
           notificationRepository.deleteAll(notifs);
           commentRepository.deleteAll(comments);
           tagRepository.deleteAll(tags);
           postRepository.deleteAll(posts);

           if(user.get().getImage() != null) {
               imageRepository.deleteById(user.get().getImage().getId());
           }

           userRepository.deleteById(id);

            return "account deleted";
        }
    }
}
