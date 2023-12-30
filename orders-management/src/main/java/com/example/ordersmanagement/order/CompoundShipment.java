package com.example.ordersmanagement.order;

public class CompoundShipment implements Shipment {
    private int capacity;

    public CompoundShipment(int capacity) {
        this.capacity = capacity;
    }

    public double calculateShipmentFees(Address address) {
        // return a random number between 50 and 200
        return (int)(Math.random() * 150) + 50;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}