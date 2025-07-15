package com.startuper.postonapp.services;

import com.startuper.postonapp.dtos.NotificationDto;
import com.startuper.postonapp.entities.Notification;
import com.startuper.postonapp.mappers.NotificationMapper;
import com.startuper.postonapp.repositories.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private INotificationRepository notificationRepository;

    @CacheEvict(value = "notificationsByUser", key = "#notificationDto.userId")
    public NotificationDto createNotification(NotificationDto notificationDto) {
        Notification notification = notificationRepository.save(notificationMapper.toEntity(notificationDto));
        return notificationMapper.toDto(notification);
    }

    @Cacheable(value = "notificationsByUser", key = "#userId")
    public List<NotificationDto> getNotifsByUserId(Long userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId);
        return notifications.stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "notificationsByUser", key = "#notification.get().user.id")
    public String readNotification(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            notification.get().setRead(true);
            notificationRepository.save(notification.get());
            return "notification read";
        } else {
            throw new IllegalArgumentException("notification not found");
        }
    }
}
