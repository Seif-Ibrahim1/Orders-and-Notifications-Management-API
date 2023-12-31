package com.example.ordersmanagement.order;

import java.time.LocalDateTime;
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

        public String createSimpleOrder(int customer_id, SimpleOrder order) {
            order.setId(orderRepository.getNextOrderId());
            Account account = new Account(customer_id, "seif", new Address());
            order.setCustomer(account);
            order.place();
            orderRepository.createOrder(customer_id, order);
            return "Created order " + order.getId() + " for customer " + customer_id + ".";
        }

        public String createCompoundOrder(int customer_id, CompoundOrder order) {
            order.setId(orderRepository.getNextOrderId());
            for(Order o : order.getOrders()) {
                createSimpleOrder(o.getCustomerId(), (SimpleOrder) o);
            }
            Account account = new Account(customer_id, "seif", new Address());
            order.setCustomer(account);
            order.place();
            orderRepository.createOrder(customer_id, order);
            return "Created order " + order.getId() + " for customer " + customer_id + ".";
        }

        public Order getOrder(int customer_id, int order_id) {
            return orderRepository.getOrder(customer_id, order_id);
        }

        public String cancelOrder(int customer_id, int order_id) {
            Order order = orderRepository.getOrder(customer_id, order_id);
            if(order.getState() == OrderState.SHIPPED) {
                if(LocalDateTime.now().isAfter(order.getShippedTime().plusMinutes(1))) {
                    return "The time for cancelling order " + order_id + " has passed.";
                }
            }

            if(order.getState() == OrderState.CANCELLED) {
                return "Order " + order_id + " is already cancelled.";
            }

            order.setState(OrderState.CANCELLED);
            orderRepository.updateOrder(customer_id, order_id, order);
            return "Order " + order_id + " is cancelled.";

        }

        public String markAsShipped(int customer_id, int order_id) {
            Order order = orderRepository.getOrder(customer_id, order_id);
            if(order.getState() == OrderState.PLACED) {
                order.setState(OrderState.SHIPPED);
                orderRepository.updateOrder(customer_id, order_id, order);
                return "Order " + order_id + " is shipped.";
            }

            return "Order " + order_id + " is not cancelled.";
            
        }


}
