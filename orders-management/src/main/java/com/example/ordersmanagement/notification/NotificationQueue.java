package com.example.ordersmanagement.notification;

import java.util.Queue;

public class NotificationQueue {
    private Queue<Notification> notifications;
    public void add(Notification notification) {
        notifications.add(notification);
    }

    public void remove(Notification notification) {
        notifications.remove(notification);
    }

    public Queue<Notification> getNotifications() {
        return notifications;
    }
}
