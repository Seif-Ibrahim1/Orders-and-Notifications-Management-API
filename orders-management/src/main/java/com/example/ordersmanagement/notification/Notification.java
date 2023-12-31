package com.example.ordersmanagement.notification;

public class Notification {
    private int id;
    private NotificationTemplate notificationTemplate;

    public Notification() {}

    public Notification(int id , NotificationTemplate notificationTemplate) {
        this.id = id;
        this.notificationTemplate = notificationTemplate;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public NotificationTemplate getNotificationTemplate() {
        return notificationTemplate;
    }


    public void setNotificationTemplate(NotificationTemplate notificationTemplate) {this.notificationTemplate = notificationTemplate;}

    public void sendNotification() {notificationTemplate.sendNotification();}

    @Override
    public String toString() {
        return "Notification{" +
                "notificationTemplate=" + notificationTemplate +
                '}';
    }
}
