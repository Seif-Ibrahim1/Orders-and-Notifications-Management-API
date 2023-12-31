package com.example.ordersmanagement.order;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.ordersmanagement.account.Account;
import com.example.ordersmanagement.account.AccountService;
import com.example.ordersmanagement.notification.NotificationService;


@Service
public class OrderService {

        final private OrderRepository orderRepository;
        final private NotificationService notificationService;
        final private AccountService accountService;

        public OrderService(OrderRepository orderRepository, NotificationService notificationService, AccountService accountService) {
            this.orderRepository = orderRepository;
            this.notificationService = notificationService;
            this.accountService = accountService;
        }

        public ArrayList<Order> getOrders(int customer_id) {
            return orderRepository.getOrders(customer_id);
        }

        public String createSimpleOrder(int customer_id, SimpleOrder order) {
            order.setId(orderRepository.getNextOrderId());
            if(accountService.getAccount(customer_id) == null) {
                return "Customer with id " + customer_id + " does not exist.";
            }
            Account account = accountService.getAccount(customer_id);
            order.setCustomer(account);
            System.out.println(order.getCustomer().getUsername());
            order.place();
            orderRepository.createOrder(customer_id, order);
            notificationService.makeOrderPlacedNotification(account.getUsername(), String.valueOf(order.getId()));
            return "Created order " + order.getId() + " for customer " + customer_id + ".";
        }

        public String createCompoundOrder(int customer_id, CompoundOrder order) {
            order.setId(orderRepository.getNextOrderId());
            for(Order o : order.getOrders()) {
                createSimpleOrder(o.getCustomerId(), (SimpleOrder) o);
            }
            Account account = accountService.getAccount(customer_id);
            order.setCustomer(account);
            order.place();
            orderRepository.createOrder(customer_id, order);
            for(Account acc : order.getSubscribers()) {
                notificationService.makeOrderPlacedNotification(acc.getUsername(), String.valueOf(order.getId()));
            }
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
            Account account = accountService.getAccount(customer_id);
            notificationService.makeOrderCancelledNotification(account.getUsername(), String.valueOf(order_id));
            return "Order " + order_id + " is cancelled.";

        }

        public String markAsShipped(int customer_id, int order_id) {
            Order order = orderRepository.getOrder(customer_id, order_id);
            if(order.getState() == OrderState.PLACED) {
                order.setState(OrderState.SHIPPED);
                orderRepository.updateOrder(customer_id, order_id, order);
                Account account = accountService.getAccount(customer_id);
                notificationService.makeOrderShippedNotification(account.getUsername(), String.valueOf(order_id), account.getAddress().getCity());
                return "Order " + order_id + " is shipped.";
            }

            return "Order " + order_id + " is not cancelled.";
            
        }


}
