package com.example.ordersmanagement.notification;

import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class NotificationService {
    final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void addNotification(Notification notification) {
        notificationRepository.addNotification(notification);
    }

    public Queue<Notification> getNotifications() {
        return notificationRepository.getNotifications();
    }
}
