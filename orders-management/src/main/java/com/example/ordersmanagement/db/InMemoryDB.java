package com.example.ordersmanagement.db;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.ordersmanagement.order.Account;
import com.example.ordersmanagement.order.Address;
import com.example.ordersmanagement.order.CompoundOrder;
import com.example.ordersmanagement.order.Order;
import com.example.ordersmanagement.order.SimpleOrder;

public class InMemoryDB implements DB {
    private static InMemoryDB instance;
    HashMap<Integer, HashMap<Integer, Order>> orders;
    private static int order_id = 1;

    private InMemoryDB() {
        orders = new HashMap<Integer, HashMap<Integer, Order>>();
        orders.put(1, new HashMap<Integer, Order>());
        SimpleOrder order = new SimpleOrder();
        order.setId(getNextOrderId());
        order.setCustomer(new Account(1, "Seif", new Address()));
        order.place();

        orders.put(2, new HashMap<Integer, Order>());
        SimpleOrder order2 = new SimpleOrder();
        order2.setId(getNextOrderId());
        order2.setCustomer(new Account(2, "seif2", new Address()));
        order2.place();

        orders.put(3, new HashMap<Integer, Order>());
        CompoundOrder compoundOrder = new CompoundOrder();
        compoundOrder.setId(getNextOrderId());
        compoundOrder.addOrder(order);
        compoundOrder.addOrder(order2);
        compoundOrder.place();
        

        orders.get(1).put(1, order);
        orders.get(2).put(2, order2);
        orders.get(3).put(3, compoundOrder);


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