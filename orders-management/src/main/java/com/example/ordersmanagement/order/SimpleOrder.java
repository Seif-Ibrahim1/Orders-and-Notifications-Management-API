package com.example.ordersmanagement.order;

import java.util.ArrayList;

public class SimpleOrder extends Order {
    // private Account customer;
    // private ArrayList<Product> products;
    private SimpleShipment shipment;


    public SimpleOrder(int id) {
        super(id);
        shipment = new SimpleShipment();
        // products = new ArrayList<Product>();
    }

    // public void addProduct(Product product) {
    //     products.add(product);
    // }

    // public void removeProduct(Product product) {
    //     products.remove(product);
    // }

    // public void setCustomer(Account customer) {
    //     this.customer = customer;
    // }

    // public Account getCustomer() { 
    //     return customer;
    // }

    // public ArrayList<Product> getProducts() {
    //     return products;
    // }

    public void notifySubscribers() {
        
    }   

    public double getShipmentFees() {
        // return shipment.calculateShipmentFees(address);
        return 0;
    }
}
