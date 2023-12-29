package com.example.ordersmanagement.order;

import java.util.ArrayList;

public abstract class Order {
    private int id;
    private double shipmentFees;
    private double cost;
    // ArrayList<Account> subscribers = new ArrayList<Account>();

    public Order(int id) {
        this.id = id;
    }

    // public void addSubscriber(Account account) {
    //     subscribers.add(account);
    // }

    // public void removeSubscriber(Account account) {
    //     subscribers.remove(account);
    // }

    public abstract void notifySubscribers();

    public void setShipmentFees(double shipmentFees) {
        this.shipmentFees = shipmentFees;
    }

    public abstract double getShipmentFees(); 
}
