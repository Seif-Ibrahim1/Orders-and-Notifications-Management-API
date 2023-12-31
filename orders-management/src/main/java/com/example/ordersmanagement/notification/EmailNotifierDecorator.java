package com.example.ordersmanagement.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailNotifierDecorator extends NotifierDecorator{
    @JsonCreator
    public EmailNotifierDecorator(@JsonProperty("wrappee") Notifier notifier) {super(notifier);}

    @Override
    public void send(NotificationTemplate notificationTemplate) {
        System.out.println("Sending email notification: " + notificationTemplate);
        super.send(notificationTemplate);
    }
}
