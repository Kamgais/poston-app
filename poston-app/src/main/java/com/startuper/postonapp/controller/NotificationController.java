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
        NotificationDto created = notificationService.createNotification(notificationDto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<NotificationDto>> findNotifsByUserId(@PathVariable("id") Long userId) {
        List<NotificationDto> notifications = notificationService.getNotifsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<String> readNotification(@PathVariable("id") Long id) {
        try {
            String result = notificationService.readNotification(id);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
