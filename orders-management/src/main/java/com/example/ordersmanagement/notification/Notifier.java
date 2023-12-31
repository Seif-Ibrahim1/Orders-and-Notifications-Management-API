package com.example.ordersmanagement.notification;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = NotifierSerializer.class)
public interface Notifier {
    void send(NotificationTemplate notificationTemplate);


    Notifier getWrappee();
}
