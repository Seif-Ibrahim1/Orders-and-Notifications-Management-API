package com.example.ordersmanagement.notification;

import com.example.ordersmanagement.db.InMemoryDB;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepository {
    private final InMemoryDB inMemoryDB;

    public NotificationRepository(InMemoryDB inMemoryDB) {
        this.inMemoryDB = inMemoryDB;
    }
    public void addNotification(Notification notification) {
        inMemoryDB.addNotification(notification);
    }

    public List<Notification> getNotifications() {
        return inMemoryDB.getNotifications();
    }

    public NotificationTemplate getOrderedTemplate() {
        return inMemoryDB.getOrderedTemplate();
    }

    public NotificationTemplate getShippedTemplate() {
        return inMemoryDB.getShippedTemplate();
    }

    public NotificationTemplate getCancelTemplate() {
        return inMemoryDB.getCancelTemplate();
    }

    // public NotificationTemplate getSignUpTemplate() {
    //     return inMemoryDB.getSignUpTemplate();
    // }

    public int getNextNotificationId() {
        return inMemoryDB.getNextNotificationId();
    }
}
