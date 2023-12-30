package com.example.ordersmanagement.notification;

import org.springframework.web.bind.annotation.*;

import java.util.Queue;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public String addNotification(@RequestBody Notification notification) {
        notificationService.addNotification(notification);
        return "Notification added";
    }

    @GetMapping
    public Queue<Notification> getNotifications() {
        return notificationService.getNotifications();
    }
}
