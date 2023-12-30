package com.example.ordersmanagement.order;

import java.util.ArrayList;

public abstract class Order {
    protected int id;
    protected double shipmentFees;
    protected double cost;
    protected ArrayList<Account> subscribers;

    public Order(int id) {
        this.id = id;
        cost = 0;
        subscribers = new ArrayList<Account>();
    }

    public void addSubscriber(Account account) {
        subscribers.add(account);
    }

    public void removeSubscriber(Account account) {
        subscribers.remove(account);
    }

    public abstract void notifySubscribers();

    public void setShipmentFees(double shipmentFees) {
        this.shipmentFees = shipmentFees;
    }

    public abstract double getShipmentFees(); 

    public void setCost(double cost) {
        this.cost = cost;
    }

    public abstract double getCost();

    public int getId() {
        return id;
    }

    public ArrayList<Account> getSubscribers() {
        return subscribers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSubscribers(ArrayList<Account> subscribers) {
        this.subscribers = subscribers;
    }

    public void place() {
        setShipmentFees(getShipmentFees());
        setCost(getCost() + shipmentFees);
        notifySubscribers();
        
    }
}
