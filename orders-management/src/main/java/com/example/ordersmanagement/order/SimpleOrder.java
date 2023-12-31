package com.example.ordersmanagement.order;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.example.ordersmanagement.account.Account;
import com.example.ordersmanagement.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SimpleOrder extends Order {
    private ArrayList<Product> products;
    private SimpleShipment shipment;
    private ArrayList<Integer> productIds;
    

    public SimpleOrder() {
        super();
        shipment = new SimpleShipment();
        products = new ArrayList<Product>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void setCustomer(Account customer) {
        this.customer = customer;
        addSubscriber(customer);
    }

    public void setState(OrderState state) {
        this.state = state;
        if(state == OrderState.SHIPPED) {
            shippedTime = LocalDateTime.now();
        }
        notifySubscribers();
    }

    @JsonIgnore
    public Account getCustomer() { 
        return customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProductIds(ArrayList<Integer> productIds) {
        this.productIds = productIds;
    }

    @JsonIgnore
    public ArrayList<Integer> getProductIds() {
        return productIds;
    }

    public void notifySubscribers() {
        System.out.println("Notifying subscribers about state " + state);
    }   

    public double getShipmentFees() {
        if(shipmentFees != 0) {
            return shipmentFees;
        }
        return shipment.calculateShipmentFees(customer.getAddress());
    }

    public void setShipmentFees(double shipmentFees) {
        this.shipmentFees = shipmentFees;
    }

    public double getCost() {
        if(cost != 0) {
            return cost;
        }
        for (Product product : products) {
            cost += product.getPrice();
        }
        return cost;
    }

    public boolean place() {
        setShipmentFees(getShipmentFees());
        setCost(getCost());
        if(customer.getBalance() < cost + shipmentFees) {
            return false;
        }
        state = OrderState.PLACED;
        notifySubscribers();
        return true;
    }
    
}
