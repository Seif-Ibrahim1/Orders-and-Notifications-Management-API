package com.example.ordersmanagement.order;

public class Account {
    private Address address;
    private String name;
    private int id;

    public Account(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    
    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{"
                + "\"address\":" + address
                + ",\"name\":\"" + name + "\""
                + ",\"id\":" + id
                + "}";
    }
}
