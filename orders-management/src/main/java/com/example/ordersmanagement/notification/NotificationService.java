package com.example.ordersmanagement.notification;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

@Service
public class NotificationService {
    final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {this.notificationRepository = notificationRepository;}

    public String addNotification(Notification notification) {
        notificationRepository.addNotification(notification);
        return "Notification added";
    }

    public HashMap<Integer, Notification> getNotifications() {
        return notificationRepository.getNotifications();
    }
}
