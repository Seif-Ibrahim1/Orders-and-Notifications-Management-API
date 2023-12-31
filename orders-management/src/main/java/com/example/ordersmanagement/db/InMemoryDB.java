package com.example.ordersmanagement.db;

import com.example.ordersmanagement.notification.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
//import javax.management.Notification;
import java.util.HashMap;
import java.util.List;

public class InMemoryDB implements DB {
    private static InMemoryDB instance;
    protected HashMap<Integer, Notification> notifications = new HashMap<>();
    protected NotificationTemplate orderedTemplate = new NotificationTemplate();
    protected NotificationTemplate shippedTemplate = new NotificationTemplate();
    protected NotificationTemplate cancelTemplate = new NotificationTemplate();
    protected NotificationTemplate signUpTemplate = new NotificationTemplate();

    private InMemoryDB() {
    }

    public static InMemoryDB getInstance() {
        if (instance == null) {
            instance = new InMemoryDB();
        }
        return instance;
    }

    public NotificationTemplate getOrderedTemplate() {
        return orderedTemplate;
    }
    public NotificationTemplate getShippedTemplate() {
        return shippedTemplate;
    }
    public NotificationTemplate getCancelTemplate() {
        return cancelTemplate;
    }
    public NotificationTemplate getSignUpTemplate() {
        return signUpTemplate;
    }

    public List<Notification> getNotifications() {

        if(!notifications.isEmpty()){
            return (ArrayList<Notification>) notifications.values();
        }
        else{
            return new ArrayList<Notification>();
        }
    }

    public void setNotifications(HashMap<Integer, Notification> notifications) {
        this.notifications = notifications;
    }

    public void setOrderedTemplate(int id , String Language, Notifier availableChannels , List<String> placeholders) {
        this.orderedTemplate.setId(id);
        this.orderedTemplate.setSubject("Order Placed");
        this.orderedTemplate.setContent("Dear %s , your order %s has been placed successfully");
        this.orderedTemplate.setLanguage(Language);
        this.orderedTemplate.setAvailableChannels(availableChannels);
        this.orderedTemplate.setPlaceholders(placeholders);
        for(int i = 0 ; i < placeholders.size() ; i++){
            orderedTemplate.getContent().replace("%s",placeholders.get(i));
        }
    }
    public void setShippedTemplate(int id , String Language, Notifier availableChannels , List<String> placeholders) {
        this.shippedTemplate.setId(id);
        this.shippedTemplate.setSubject("Order Shipped");
        this.shippedTemplate.setContent("Dear %s , your order %s has been shipped successfully to this address %s");
        this.shippedTemplate.setLanguage(Language);
        this.shippedTemplate.setAvailableChannels(availableChannels);
        this.shippedTemplate.setPlaceholders(placeholders);
        for(int i = 0 ; i < placeholders.size() ; i++){
            shippedTemplate.getContent().replace("%s",placeholders.get(i));
        }
    }

    public void setCancelTemplate(int id , String Language, Notifier availableChannels , List<String> placeholders) {
        this.cancelTemplate.setId(id);
        this.cancelTemplate.setSubject("Order Cancelled");
        this.cancelTemplate.setContent("Dear %s , your order %s has been cancelled successfully");
        this.cancelTemplate.setLanguage(Language);
        this.cancelTemplate.setAvailableChannels(availableChannels);
        this.cancelTemplate.setPlaceholders(placeholders);
        for(int i = 0 ; i < placeholders.size() ; i++){
            cancelTemplate.getContent().replace("%s",placeholders.get(i));
        }
    }

    public void setSignUpTemplate(int id , String Language, Notifier availableChannels , List<String> placeholders) {
        this.signUpTemplate.setId(id);
        this.signUpTemplate.setSubject("Sign Up");
        this.signUpTemplate.setContent("Dear %s , you have signed up successfully");
        this.signUpTemplate.setLanguage(Language);
        this.signUpTemplate.setAvailableChannels(availableChannels);
        this.signUpTemplate.setPlaceholders(placeholders);
        for(int i = 0 ; i < placeholders.size() ; i++){
            signUpTemplate.getContent().replace("%s",placeholders.get(i));
        }
    }
}