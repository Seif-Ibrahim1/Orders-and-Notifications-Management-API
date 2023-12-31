package com.example.ordersmanagement.statistics;

import java.util.HashMap;

import com.example.ordersmanagement.notification.NotificationType;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Statistics {

    private HashMap<NotificationType, Integer> notificationTemplateCount;
    private HashMap<String, Integer> emailCount;
    private HashMap<String, Integer> phoneNumCount;
    private String mostNotifiedEmail;
    private String mostNotifiedPhoneNum;
    private String mostUsedTemplate;

    public Statistics() {
        notificationTemplateCount = new HashMap<NotificationType, Integer>();
        emailCount = new HashMap<String, Integer>();
        phoneNumCount = new HashMap<String, Integer>();
        mostNotifiedEmail = "";
        mostNotifiedPhoneNum = "";
        mostUsedTemplate = "";
    }

    
    public String getMostNotifiedEmail() {
        return mostNotifiedEmail;
    }

    public void setMostNotifiedEmail(String mostNotifiedEmail) {
        this.mostNotifiedEmail = mostNotifiedEmail;
    }

    public String getMostNotifiedPhoneNum() {
        return mostNotifiedPhoneNum;
    }

    public void setMostNotifiedPhoneNum(String mostNotifiedPhoneNum) {
        this.mostNotifiedPhoneNum = mostNotifiedPhoneNum;
    }

    public String getMostUsedTemplate() {
        return mostUsedTemplate;
    }

    public void setMostUsedTemplate(String mostUsedTemplate) {
        this.mostUsedTemplate = mostUsedTemplate;
    }

    @JsonIgnore
    public HashMap<NotificationType, Integer> getNotificationTemplateCount() {
        return notificationTemplateCount;
    }

    @JsonIgnore
    public HashMap<String, Integer> getEmailCount() {
        return emailCount;
    }

    @JsonIgnore
    public HashMap<String, Integer> getPhoneNumCount() {
        return phoneNumCount;
    }

    public void updateMostNotifiedEmail() {
        int max = 0;
        for (String key : emailCount.keySet()) {
            if (emailCount.get(key) > max) {
                System.out.println("updateMostNotifiedEmail");
                max = emailCount.get(key);
                mostNotifiedEmail = key;
            }
        }
    }

    public void updateMostNotifiedPhoneNum() {
        int max = 0;
        for (String key : phoneNumCount.keySet()) {
            if (phoneNumCount.get(key) > max) {
                max = phoneNumCount.get(key);
                mostNotifiedPhoneNum = key;
            }
        }
    }

    public void updateMostUsedTemplate() {
        int max = 0;
        for (NotificationType key : notificationTemplateCount.keySet()) {
            if (notificationTemplateCount.get(key) > max) {
                max = notificationTemplateCount.get(key);
                mostUsedTemplate = key.toString();
            }
        }
    }

    

}
