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
        subscribers.addAll(order.getSubscribers());
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        subscribers.removeAll(order.getSubscribers());
    }

    public void notifySubscribers() {
        
    }

    public double getShipmentFees() {
        return shipment.calculateShipmentFees(orders.get(0).getSubscribers().get(0).getAddress());
    }

    public double getCost() {
        for (Order order : orders) {
            cost += order.getCost();
        }
        return cost;
    }
}
