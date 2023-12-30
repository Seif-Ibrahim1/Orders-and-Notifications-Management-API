package com.example.ordersmanagement.notification;

public abstract class NotifierDecorator implements Notifier{
    protected Notifier wrappee;

    public NotifierDecorator(Notifier wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void send(NotificationTemplate notificationTemplate) {
        wrappee.send(notificationTemplate);
    }
}
