package com.example.ordersmanagement.notification;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Repository
public class NotificationRepository {
//    private Queue<Notification> notifications;
    private NotificationQueue notifications;
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public Queue<Notification> getNotifications() {
        return notifications.getNotifications();
    }
}
