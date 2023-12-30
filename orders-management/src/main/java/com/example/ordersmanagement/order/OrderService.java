package com.example.ordersmanagement.order;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

        final private OrderRepository orderRepository;

        public OrderService(OrderRepository orderRepository) {
            this.orderRepository = orderRepository;
        }

        public ArrayList<Order> getOrders(int customer_id) {
            return orderRepository.getOrders(customer_id);
        }

        // public String createOrder(int customer_id, Order order) {
        //     return 
        // }

        public String getOrder(int customer_id, int order_id) {
            return orderRepository.getOrder(customer_id, order_id);
        }

        // public String cancelOrder(int customer_id, int order_id) {
        //     return 
        // }


}
