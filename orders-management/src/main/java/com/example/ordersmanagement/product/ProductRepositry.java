package com.example.ordersmanagement.product;

import com.example.ordersmanagement.db.InMemoryDB;

import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositry {
    private InMemoryDB inMemoryDB;
    
    public ProductRepositry(InMemoryDB inMemoryDB) {
        this.inMemoryDB = inMemoryDB;
    }

    public Product getProduct(int id) {
        return inMemoryDB.getProduct(id);
    }

    public Set<Integer> getProductIds() {
        return inMemoryDB.getProductIds();
    }


}
