package com.example.ordersmanagement.order;

import java.util.ArrayList;

public class CompoundOrder extends Order {
    CompoundShipment shipment;
    ArrayList<Order> orders;

    public CompoundOrder(int id) {
        super(id);
        orders = new ArrayList<Order>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public void notifySubscribers() {
        
    }

    public double getShipmentFees() {
        // return shipment.calculateShipmentFees(address);
        return 0;
    }
}
