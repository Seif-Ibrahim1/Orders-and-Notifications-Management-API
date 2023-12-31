package com.example.ordersmanagement.notification;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NotificationService {
    final NotificationRepository notificationRepository;
    NotificationQueue notificationQueue;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
        notificationQueue = new NotificationQueue();
    }

    public NotificationTemplate copyTemplate(NotificationTemplate template) {
        NotificationTemplate copy = new NotificationTemplate();
        copy.setLanguage(template.getLanguage());
        copy.setAvailableChannels(template.getAvailableChannels()); 
        copy.setContent(template.getContent());
        copy.setLanguage(template.getLanguage());
        copy.setType(template.getType());
        copy.setId(template.getId());
        copy.setSubject(template.getSubject());

        
        return copy;
    }

    public String makeOrderPlacedNotification(String clientName, String orderID) {
        List<String> placeholders = new ArrayList<>();
        System.out.println("client name to notify : " + clientName);
        System.out.println("order id to notify : " + orderID);
        placeholders.add(clientName);
        placeholders.add(orderID);
        NotificationTemplate orderedTemplate = copyTemplate(notificationRepository.getOrderedTemplate());
        orderedTemplate.setPlaceholders(placeholders);
        Notification notification = new Notification(notificationRepository.getNextNotificationId(), orderedTemplate);
        notificationQueue.add(notification);
        return "Notification added";
    }

    public String makeOrderShippedNotification(String clientName, String orderID, String address) {
        List<String> placeholders = new ArrayList<>();
        placeholders.add(clientName);
        placeholders.add(orderID);
        placeholders.add(address);
        NotificationTemplate shippedTemplate = copyTemplate(notificationRepository.getShippedTemplate());
        shippedTemplate.setPlaceholders(placeholders);
        Notification notification = new Notification(notificationRepository.getNextNotificationId(), shippedTemplate);
        notificationQueue.add(notification);
        return "Notification added";
    }

    public String makeOrderCancelledNotification(String clientName, String orderID) {
        List<String> placeholders = new ArrayList<>();
        placeholders.add(clientName);
        placeholders.add(orderID);
        NotificationTemplate cancelTemplate = copyTemplate(notificationRepository.getCancelTemplate());
        cancelTemplate.setPlaceholders(placeholders);
        Notification notification = new Notification(notificationRepository.getNextNotificationId(), cancelTemplate);
        notificationQueue.add(notification);
        return "Notification added";
    }
    

    public List<Notification> getNotifications() {
        return new ArrayList<>(notificationQueue.getNotifications());
    }
}
