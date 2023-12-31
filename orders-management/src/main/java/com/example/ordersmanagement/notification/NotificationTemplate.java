package com.example.ordersmanagement.notification;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NotificationTemplate {
    private int id ;
    private NotificationType type ;
    private String subject ;
    private String content ;
    private String Language ;
    private Notifier availableChannels ;
    private List<String> placeholders = new ArrayList<>();
    private String email = "";
    private String phoneNumber = "";

    public NotificationTemplate(int id , NotificationType type , String Language , Notifier availableChannels , List<String> placeholders) {
        this.id = id;
        this.type = type;
        this.Language = Language;
        this.availableChannels = availableChannels;
        this.placeholders = placeholders;
        // setSubjectAndContent();
        for(int i = 0 ; i < placeholders.size() ; i++){
            content.replace("%s",placeholders.get(i));
        }
    }

    public NotificationTemplate() {}

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

    public String getSubject() {return subject;}

    public void setSubject(String subject) {this.subject = subject;}

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}

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

    @JsonIgnore
    public List<String> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(List<String> placeholders) {
        content = String.format(content, placeholders.toArray());
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void sendNotification() {availableChannels.send(this);}

}
