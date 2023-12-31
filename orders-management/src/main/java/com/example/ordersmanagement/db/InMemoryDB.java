package com.example.ordersmanagement.db;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.ordersmanagement.order.Order;

public class InMemoryDB implements DB {
    private static InMemoryDB instance;
    HashMap<Integer, HashMap<Integer, Order>> orders;
    private static int order_id = 1;

    private InMemoryDB() {
        orders = new HashMap<Integer, HashMap<Integer, Order>>();
    }

    public static InMemoryDB getInstance() {
        if (instance == null) {
            instance = new InMemoryDB();
        }
        return instance;
    }

    public ArrayList<Order> getOrders(int customer_id) {
            if(orders.containsKey(customer_id)) {
                return new ArrayList<Order>(orders.get(customer_id).values());
            }
            return new ArrayList<Order>();
    }

    public String createOrder(int customer_id, Order order) {
        if (orders.containsKey(customer_id)) {
            order.setId(orders.get(customer_id).size()+1);
            orders.get(customer_id).put(order.getId(), order);
        } else {
            order.setId(1);
            HashMap<Integer, Order> customer_orders = new HashMap<Integer, Order>();
            customer_orders.put(order.getId(), order);
            orders.put(customer_id, customer_orders);
        }
        return  "Created order " + order.getId() + " for customer " + customer_id + ".";
    }

    public Order getOrder(int customer_id, int order_id) {
        return orders.get(customer_id).get(order_id);
    }

    public int getNextOrderId() {
        return order_id++;
    }

    public void updateOrder(int customer_id, int order_id, Order order) {
        orders.get(customer_id).put(order_id, order);
    }

    
}