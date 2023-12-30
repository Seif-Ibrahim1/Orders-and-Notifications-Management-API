package com.example.ordersmanagement.notification;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SMSNotifierDecorator.class, name = "SMSNotifierDecorator"),
        @JsonSubTypes.Type(value = EmailNotifierDecorator.class, name = "EmailNotifierDecorator")
        // Add more implementations as needed
})
public interface Notifier {
    void send(NotificationTemplate notificationTemplate);
}
