package com.example.ordersmanagement.order;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.ordersmanagement.db.InMemoryDB;

@Repository
public class OrderRepository {
        private final InMemoryDB inMemoryDB;

        public OrderRepository(InMemoryDB inMemoryDB) {
            this.inMemoryDB = inMemoryDB;
        }


        public ArrayList<Order> getOrders(int customer_id) {
            return inMemoryDB.getOrders(customer_id);
        }

        // public String createOrder(int customer_id, Order order) {
        //     return 
        // }

        public String getOrder(int customer_id, int order_id) {
            return inMemoryDB.getOrder(customer_id, order_id);
        }

        // public String cancelOrder(int customer_id, int order_id) {
        //     return 
        // }
}
