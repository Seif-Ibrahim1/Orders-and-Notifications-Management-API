package com.example.ordersmanagement.statistics;

import org.springframework.stereotype.Service;

import com.example.ordersmanagement.notification.Notification;

@Service
public class StatisticsService {

    private Statistics statistics;

    public StatisticsService() {
        statistics = new Statistics();
    }

    public void addNotification(Notification notification) {
        if(!notification.getNotificationTemplate().getEmail().equals("") 
                    && notification.getNotificationTemplate().getEmail() != null) {
            if(!statistics.getEmailCount().containsKey(notification.getNotificationTemplate().getEmail())) {
                statistics.getEmailCount().put(notification.getNotificationTemplate().getEmail(), 1);
            } else {
                int num = statistics.getEmailCount().get(notification.getNotificationTemplate().getEmail());
                statistics.getEmailCount().put(notification.getNotificationTemplate().getEmail(), num + 1);
            }
            statistics.updateMostNotifiedEmail();
        } 

        if(!notification.getNotificationTemplate().getPhoneNumber().equals("") 
                    && notification.getNotificationTemplate().getPhoneNumber() != null) {
            if(!statistics.getPhoneNumCount().containsKey(notification.getNotificationTemplate().getPhoneNumber())) {
                statistics.getPhoneNumCount().put(notification.getNotificationTemplate().getPhoneNumber(), 1);
            } else {
                int num = statistics.getPhoneNumCount().get(notification.getNotificationTemplate().getPhoneNumber());
                statistics.getPhoneNumCount().put(notification.getNotificationTemplate().getPhoneNumber(), num + 1);
            }
            
            statistics.updateMostNotifiedPhoneNum();
            
        }
        System.out.println("phone : " + notification.getNotificationTemplate().getPhoneNumber());
        System.out.println("type : " + notification.getNotificationTemplate().getType());

        if(!statistics.getNotificationTemplateCount().containsKey(notification.getNotificationTemplate().getType())) {
            statistics.getNotificationTemplateCount().put(notification.getNotificationTemplate().getType(), 1);
        } else {
            int num = statistics.getNotificationTemplateCount().get(notification.getNotificationTemplate().getType());
            statistics.getNotificationTemplateCount().put(notification.getNotificationTemplate().getType(), num + 1);
        }

        statistics.updateMostUsedTemplate();
        
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
