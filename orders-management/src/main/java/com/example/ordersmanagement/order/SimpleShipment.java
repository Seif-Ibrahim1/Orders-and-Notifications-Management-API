package com.example.ordersmanagement.order;

public class SimpleShipment implements Shipment {
    public double calculateShipmentFees(Address address) {
        // return a random number between 50 and 200
        return (int)(Math.random() * 150) + 50;
    }
}
