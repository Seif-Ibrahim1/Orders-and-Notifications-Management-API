package com.example.ordersmanagement.notification;

import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;

public class NotificationQueue {
    private Queue<Notification> notifications = new LinkedList<>();
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void add(Notification notification) {
        notifications.add(notification);
        scheduleNotificationRemoval(notification, 10, TimeUnit.SECONDS); // Schedule removal after 10 seconds (adjust as needed)
    }

    public void remove(Notification notification) {
        notifications.remove(notification);
    }

    public Queue<Notification> getNotifications() {
        return notifications;
    }

//    @Override
//    public String toString() {
//        return "NotificationQueue{" +
//                "notifications=" + notifications +
//                '}';
//    }

    public boolean isEmpty() {
        return notifications.isEmpty();
    }

    private void scheduleNotificationRemoval(Notification notification, long delay, TimeUnit timeUnit) {
        scheduler.schedule(() -> {
            remove(notification);
        }, delay, timeUnit);
    }
}
