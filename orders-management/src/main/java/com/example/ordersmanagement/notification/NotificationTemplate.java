package com.example.ordersmanagement.notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationTemplate {
    private int id ;
    private NotificationType type ;
    private String content ;
    private String Language ;
    private Notifier availableChannels ;
    private List<String> placeholders = new ArrayList<>();

    public NotificationTemplate(int id , NotificationType type, String content, String Language, Notifier availableChannels, List<String> placeholders) {
        this.id = id ;
        this.type = type;
        this.content = content;
        this.Language = Language;
        this.availableChannels = availableChannels;
        this.placeholders = placeholders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public Notifier getAvailableChannels() {
        return availableChannels;
    }

    public void setAvailableChannels(Notifier availableChannels) {
        this.availableChannels = availableChannels;
    }

    public List<String> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(List<String> placeholders) {
        this.placeholders = placeholders;
    }

    public void sendNotification() {
        availableChannels.send(new Notification(this));
    }
}
