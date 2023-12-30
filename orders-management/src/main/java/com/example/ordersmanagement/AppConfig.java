package com.example.ordersmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.ordersmanagement.db.InMemoryDB;

@Configuration
public class AppConfig {

    @Bean
    public InMemoryDB inMemoryDB() {
        return InMemoryDB.getInstance();
    }
}
