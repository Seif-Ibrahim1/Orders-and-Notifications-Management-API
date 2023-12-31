package com.example.ordersmanagement.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SMSNotifierDecorator extends NotifierDecorator {

        @JsonCreator
        public SMSNotifierDecorator(@JsonProperty("wrappee") Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(NotificationTemplate notificationTemplate) {
            System.out.println("Sending SMS: " + notificationTemplate);
            super.send(notificationTemplate);
        }

        @Override
        public Notifier getWrappee() {
            return wrappee;
        }
}
