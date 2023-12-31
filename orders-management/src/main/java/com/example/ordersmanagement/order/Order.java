package com.example.ordersmanagement.order;

import java.time.*;
import java.util.ArrayList;

import com.example.ordersmanagement.account.Account;
import com.example.ordersmanagement.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Order {
    protected int id;
    protected double shipmentFees;
    protected double cost;
    protected ArrayList<Account> subscribers;
    protected OrderState state;
    protected Account customer;
    protected int customerId;
    protected ArrayList<Product> products;
    protected LocalDateTime shippedTime;

    public Order() {
        cost = 0.0;
        subscribers = new ArrayList<Account>();
        products = new ArrayList<Product>();
    }

    public void addSubscriber(Account account) {
        subscribers.add(account);
    }

    public void removeSubscriber(Account account) {
        subscribers.remove(account);
    }

    public abstract void setState(OrderState state);


    public OrderState getState() {
        return state;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomer(Account customer) {
        this.customer = customer;
        addSubscriber(customer);
    }

    @JsonIgnore
    public LocalDateTime getShippedTime() {
        return shippedTime;
    }

    public abstract void notifySubscribers();

    public abstract void setShipmentFees(double shipmentFees);

    public abstract double getShipmentFees(); 

    public void setCost(double cost) {
        this.cost = cost;
    }

    public abstract double getCost();

    public int getId() {
        return id;
    }

    @JsonIgnore
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
        setCost(getCost());
        state = OrderState.PLACED;
        notifySubscribers();
        
    }

}
