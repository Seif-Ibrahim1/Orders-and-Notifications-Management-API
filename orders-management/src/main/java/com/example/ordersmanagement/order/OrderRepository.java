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

        public void createOrder(int customer_id, Order order) {
            inMemoryDB.createOrder(customer_id, order);
        }

        public Order getOrder(int customer_id, int order_id) {
            return inMemoryDB.getOrder(customer_id, order_id);
        }

        public int getNextOrderId() {
            return inMemoryDB.getNextOrderId();
        }

        public void updateOrder(int customer_id, int order_id, Order order) {
            inMemoryDB.updateOrder(customer_id, order_id, order);
        }
}
