package com.example.ordersmanagement.order;

public class CompoundShipment implements Shipment {
    private int capacity;

    public CompoundShipment(int capacity) {
        this.capacity = capacity;
    }

    // public double calculateShipmentFees(Address address) {
    //     return 0;
    // }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}