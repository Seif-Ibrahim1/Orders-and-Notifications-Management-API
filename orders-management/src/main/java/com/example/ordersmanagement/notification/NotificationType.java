package com.example.ordersmanagement.notification;
import com.example.ordersmanagement.db.*;

import java.util.List;

public enum NotificationType {
    PLACED,
    CANCELLED,
    SHIPPED,
    SIGNED_UP;

    public void setTemplate(int id , String Language, Notifier availableChannels , List<String> placeholders){
        switch (this){
            case PLACED:
                InMemoryDB.getInstance().setOrderedTemplate(id , Language , availableChannels , placeholders);
                break;
            case CANCELLED:
                InMemoryDB.getInstance().setCancelTemplate(id , Language , availableChannels , placeholders);
                break;
            case SHIPPED:
                InMemoryDB.getInstance().setShippedTemplate(id , Language , availableChannels , placeholders);
                break;
            case SIGNED_UP:
                InMemoryDB.getInstance().setSignUpTemplate(id , Language , availableChannels , placeholders);
                break;
        }
    }


    public NotificationTemplate getTemplate(){
        switch (this){
            case PLACED:
                return InMemoryDB.getInstance().getOrderedTemplate();
            case CANCELLED:
                return InMemoryDB.getInstance().getCancelTemplate();
            case SHIPPED:
                return InMemoryDB.getInstance().getShippedTemplate();
            case SIGNED_UP:
                return InMemoryDB.getInstance().getSignUpTemplate();
        }
        return null;
    }
}
