package com.example.ordersmanagement.notification;

public class EmailNotifierDecorator extends NotifierDecorator{
    public EmailNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(Notification notification) {
        super.send(notification);
        System.out.println("Sending email notification: " + notification);
    }
}
