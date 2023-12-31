package com.example.ordersmanagement.category;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.example.ordersmanagement.db.InMemoryDB;


@Repository
public class CategoryRepositry {
    private InMemoryDB inMemoryDB;

    public CategoryRepositry(InMemoryDB inMemoryDB) {
        this.inMemoryDB = inMemoryDB;
    }

    public Category getCategory(int id) {
        return inMemoryDB.getCategory(id);
    }
    

    public Set<Integer> getCategoryIds() {
        return inMemoryDB.getCategoryIds();
    }

}
