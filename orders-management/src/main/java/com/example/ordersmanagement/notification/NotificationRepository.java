package com.example.ordersmanagement.notification;

import com.example.ordersmanagement.db.InMemoryDB;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

@Repository
public class NotificationRepository {
    public void addNotification(Notification notification) {
        InMemoryDB.getInstance().getNotifications().put(notification.getId(), notification);
    }

    public HashMap<Integer, Notification> getNotifications() {
        return InMemoryDB.getInstance().getNotifications();
    }
}
