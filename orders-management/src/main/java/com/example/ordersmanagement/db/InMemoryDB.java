package com.example.ordersmanagement.db;

public class InMemoryDB implements DB {
    private static InMemoryDB instance;

    private InMemoryDB() {
        
    }

    public static InMemoryDB getInstance() {
        if (instance == null) {
            instance = new InMemoryDB();
        }
        return instance;
    }

    
}