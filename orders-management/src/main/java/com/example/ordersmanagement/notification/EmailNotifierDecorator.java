package com.example.ordersmanagement.notification;

public class EmailNotifierDecorator extends NotifierDecorator{
    public EmailNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending email notification: " + message);
    }
}
