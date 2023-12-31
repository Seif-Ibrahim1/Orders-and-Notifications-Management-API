package com.example.ordersmanagement.notification;

import org.springframework.stereotype.Service;

import com.example.ordersmanagement.statistics.StatisticsService;

import java.util.ArrayList;
import java.util.List;


@Service
public class NotificationService {
    final NotificationRepository notificationRepository;
    NotificationQueue notificationQueue;
    private final StatisticsService statisticsService;


    public NotificationService(NotificationRepository notificationRepository,
            StatisticsService statisticsService) {
        this.notificationRepository = notificationRepository;
        notificationQueue = new NotificationQueue();
        this.statisticsService = statisticsService;
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

    public String makeOrderPlacedNotification(String clientName, String orderID, String email) {
        List<String> placeholders = new ArrayList<>();
        System.out.println("client name to notify : " + clientName);
        System.out.println("order id to notify : " + orderID);
        placeholders.add(clientName);
        placeholders.add(orderID);
        NotificationTemplate orderedTemplate = copyTemplate(notificationRepository.getOrderedTemplate());
        orderedTemplate.setPlaceholders(placeholders);
        orderedTemplate.setEmail(email);
        Notification notification = new Notification(notificationRepository.getNextNotificationId(), orderedTemplate);
        notificationQueue.add(notification);
        statisticsService.addNotification(notification);
        return "Notification added";
    }

    public String makeOrderShippedNotification(String clientName, String orderID, String address, String email, String phoneNumber) {
        List<String> placeholders = new ArrayList<>();
        placeholders.add(clientName);
        placeholders.add(orderID);
        placeholders.add(address);
        NotificationTemplate shippedTemplate = copyTemplate(notificationRepository.getShippedTemplate());
        shippedTemplate.setPlaceholders(placeholders);
        shippedTemplate.setEmail(email);
        shippedTemplate.setPhoneNumber(phoneNumber);
        Notification notification = new Notification(notificationRepository.getNextNotificationId(), shippedTemplate);
        notificationQueue.add(notification);
        statisticsService.addNotification(notification);
        return "Notification added";
    }

    public String makeOrderCancelledNotification(String clientName, String orderID, String email, String phoneNumber) {
        List<String> placeholders = new ArrayList<>();
        placeholders.add(clientName);
        placeholders.add(orderID);
        NotificationTemplate cancelTemplate = copyTemplate(notificationRepository.getCancelTemplate());
        cancelTemplate.setPlaceholders(placeholders);
        cancelTemplate.setEmail(email);
        cancelTemplate.setPhoneNumber(phoneNumber);
        Notification notification = new Notification(notificationRepository.getNextNotificationId(), cancelTemplate);
        notificationQueue.add(notification);
        statisticsService.addNotification(notification);
        return "Notification added";
    }
    

    public List<Notification> getNotifications() {
        return new ArrayList<>(notificationQueue.getNotifications());
    }
}
