package com.example.ordersmanagement.notification;

public class Notification {
    private NotificationTemplate notificationTemplate;

    public Notification(NotificationTemplate notificationTemplate) {
        this.notificationTemplate = notificationTemplate;
    }

    public NotificationTemplate getNotificationTemplate() {
        return notificationTemplate;
    }

    public void setNotificationTemplate(NotificationTemplate notificationTemplate) {
        this.notificationTemplate = notificationTemplate;
    }
}
