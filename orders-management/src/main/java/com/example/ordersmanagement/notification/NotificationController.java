package com.example.ordersmanagement.notification;

import com.example.ordersmanagement.db.InMemoryDB;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {this.notificationService = notificationService;
        InMemoryDB.getInstance().setOrderedTemplate(1 , "en" , new EmailNotifierDecorator(null), List.of("Shahd" , "12358649"));
    }

    @PostMapping()
    public String addNotification(@RequestBody Notification notification) {return notificationService.addNotification(notification);}

    @GetMapping
    public List<Notification> getNotifications() {
        return notificationService.getNotifications();
    }
}
