package com.startuper.postonapp.controller;


import com.startuper.postonapp.dtos.NotificationDto;
import com.startuper.postonapp.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("")
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
        return notificationService.createNotification(notificationDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<NotificationDto>> findNotifsByUserId(@PathVariable("id") Long userId) {
        return notificationService.getNotifsByUserId(userId);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<String> readNotification(@PathVariable("id") Long id) {
        return notificationService.readNotification(id);
    }
}
