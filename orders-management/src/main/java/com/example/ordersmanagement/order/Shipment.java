package com.example.ordersmanagement.order;

import com.example.ordersmanagement.account.address.Address;
public interface Shipment {
    public double calculateShipmentFees(Address address); 
}
