package com.example.ordersmanagement.notification;

public class SMSNotifierDecorator extends NotifierDecorator {

        public SMSNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(Notification notification) {
            super.send(notification);
            System.out.println("Sending SMS: " + notification);

        }
}
