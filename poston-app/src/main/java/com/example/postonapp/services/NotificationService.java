package com.example.postonapp.services;

import com.example.postonapp.dtos.NotificationDto;
import com.example.postonapp.entities.Notification;
import com.example.postonapp.mappers.NotificationMapper;
import com.example.postonapp.repositories.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private INotificationRepository notificationRepository;

    public ResponseEntity<NotificationDto> createNotification(NotificationDto notificationDto) {
        Notification notification = notificationRepository.save(notificationMapper.toEntity(notificationDto));

        return ResponseEntity.ok().body(notificationMapper.toDto(notification));
    }



    public ResponseEntity<List<NotificationDto>> getNotifsByUserId(Long userId) {
       List<Notification> notifications = notificationRepository.findAllByUserId(userId);
       List<NotificationDto> notificationDtos = notifications.stream().map(e->notificationMapper.toDto(e)).collect(Collectors.toList());

       return ResponseEntity.ok().body(notificationDtos);
    }


    public ResponseEntity<String> readNotification(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        notification.get().setRead(true);
        notificationRepository.save(notification.get());
        return ResponseEntity.ok().body("notification read");
    }
}
