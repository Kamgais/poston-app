package com.startuper.postonapp.mappers;

import com.startuper.postonapp.dtos.NotificationDto;
import com.startuper.postonapp.entities.Notification;
import com.startuper.postonapp.entities.Post;
import com.startuper.postonapp.entities.User;
import com.startuper.postonapp.repositories.IPostRepository;
import com.startuper.postonapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class NotificationMapper implements Mapper<Notification, NotificationDto>{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private PostMapper postMapper;
    @Override
    public Notification toEntity(NotificationDto notificationDto) {
        if(notificationDto == null) {
            return null;
        } else {
            Optional<User> user = userRepository.findById(notificationDto.getUserId());
            Optional<Post> post = postRepository.findById(notificationDto.getPost().getId());
            return Notification.builder()
                    .message(notificationDto.getMessage())
                    .dateCreated(notificationDto.getDateCreated())
                    .user(user.get())
                    .post(post.get())
                    .isRead(notificationDto.isRead())
                    .build();
        }
    }

    @Override
    public NotificationDto toDto(Notification notification) {
        if(notification == null) {
            return null;
        } else {
            return NotificationDto.builder()
                    .id(notification.getId())
                    .message(notification.getMessage())
                    .dateCreated(notification.getDateCreated())
                    .userId(notification.getUser().getId())
                    .post(postMapper.toDto(notification.getPost()))
                    .isRead(notification.isRead())
                    .build();
        }
    }
}
