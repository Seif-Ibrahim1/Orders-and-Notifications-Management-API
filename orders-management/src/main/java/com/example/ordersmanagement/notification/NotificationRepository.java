package com.example.ordersmanagement.notification;

import com.example.ordersmanagement.db.InMemoryDB;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepository {
    public void addNotification(Notification notification) {
        InMemoryDB.getInstance().addNotification(notification);
    }

    public List<Notification> getNotifications() {
        return InMemoryDB.getInstance().getNotifications();
    }

    public NotificationTemplate getOrderedTemplate() {
        return InMemoryDB.getInstance().getOrderedTemplate();
    }

    public NotificationTemplate getShippedTemplate() {
        return InMemoryDB.getInstance().getShippedTemplate();
    }

    public NotificationTemplate getCancelTemplate() {
        return InMemoryDB.getInstance().getCancelTemplate();
    }

    // public NotificationTemplate getSignUpTemplate() {
    //     return InMemoryDB.getInstance().getSignUpTemplate();
    // }

    public int getNextNotificationId() {
        return InMemoryDB.getInstance().getNextNotificationId();
    }
}
