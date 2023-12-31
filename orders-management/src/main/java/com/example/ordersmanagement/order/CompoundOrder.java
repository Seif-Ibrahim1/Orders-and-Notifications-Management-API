package com.example.ordersmanagement.order;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompoundOrder extends Order {
    CompoundShipment shipment;
    ArrayList<Order> orders;

    public CompoundOrder() {
        super();
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

    public void setState(OrderState state) {
        this.state = state;
        for (Order order : orders) {
            order.setState(state);
        }
    }

    public void notifySubscribers() {
        
    }

    public void setShipmentFees(double shipmentFees) {
        this.shipmentFees = shipmentFees;
        for (Order order : orders) {
            order.setShipmentFees(shipmentFees);
        }
    }

    @JsonIgnore
    public double getShipmentFees() {
        shipment = new CompoundShipment(subscribers.size());
        System.out.println("Workeeeeeeeeeeeed");
        System.out.println("orders size is " + orders.size());
        System.out.println("first order is " + orders.get(0));
        System.out.println("Customers are " + orders.get(0).getSubscribers());
        shipmentFees = shipment.calculateShipmentFees(orders.get(0).getSubscribers().get(0).getAddress());
        System.out.println("shipment fees are " + shipmentFees);
        System.out.println("subscribers are " + subscribers.size());

        return shipmentFees;
    }

    @JsonIgnore
    public double getCost() {
        for (Order order : orders) {
            cost += order.getCost();
        }
        return cost;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<SimpleOrder> orders) {
        for (SimpleOrder order : orders) {
            System.out.println("Adding order " + order.getId() + " to compound order ");
            System.out.println("order products are " + order.getProducts());
            System.out.println("order customer is " + order.getCustomer());
            addOrder(order);
        }
    }

    public boolean place() {

        for(Order order : orders) {
            addSubscriber(order.customer);
        }

        setShipmentFees(getShipmentFees());
        for (Order order : orders) {
            if(!order.place()) {
                return false;
            }
        }
        state = OrderState.PLACED;
        return true;
        
    }

}
