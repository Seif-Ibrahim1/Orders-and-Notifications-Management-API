package com.example.ordersmanagement.order;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.ordersmanagement.account.Account;
import com.example.ordersmanagement.account.AccountService;
import com.example.ordersmanagement.notification.NotificationService;
import com.example.ordersmanagement.product.Product;
import com.example.ordersmanagement.product.ProductService;


@Service
public class OrderService {

        final private OrderRepository orderRepository;
        final private NotificationService notificationService;
        final private AccountService accountService;
        final private ProductService productService;

        public OrderService(OrderRepository orderRepository,
         NotificationService notificationService, AccountService accountService, ProductService productService) {
            this.orderRepository = orderRepository;
            this.notificationService = notificationService;
            this.accountService = accountService;
            this.productService = productService;

        }

        public ArrayList<Order> getOrders(int customer_id) {
            return orderRepository.getOrders(customer_id);
        }

        public boolean validateSimpleOrder(int customer_id, SimpleOrder order) {
            if(accountService.getAccount(customer_id) == null) {
                return false;
            }
            Account account = accountService.getAccount(customer_id);
            order.setCustomer(account);

            for(Product product : order.getProducts()) {
                if(productService.getProduct(product.getId()) == null) {
                    return false;
                }
                // check on quantity
                if(product.getRemainingNum() == 0) {
                    return false;
                }
            }
            System.out.println(order.getCustomer().getUsername());
            return true;
        }

        public String createSimpleOrder(int customer_id, SimpleOrder order) {
            if(customer_id != order.getCustomerId()) {
                return "Customer id in the url does not match the customer id in the order.";
            }
            if(!validateSimpleOrder(customer_id, order)) {
                return "Customer with id " + customer_id + " does not exist.";
            }
            order.setId(orderRepository.getNextOrderId());
            System.out.println("Set the order id to " + order.getId());
            if(!order.place()) {
                return "Customer with id " + customer_id + " does not have enough balance.";
            }
            
            orderRepository.createOrder(customer_id, order);
            order.getCustomer().setBalance(order.getCustomer().getBalance() - (order.getCost() + order.getShipmentFees()));
            System.out.println("Set the same order id to " + order.getId());
            notificationService.makeOrderPlacedNotification(order.getCustomer().getUsername(), String.valueOf(order.getId()));
            return "Created order " + order.getId() + " for customer " + customer_id + ".";
        }

        public String createCompoundOrder(int customer_id, CompoundOrder order) {
            if(customer_id != order.getCustomerId()) {
                return "Customer id in the url does not match the customer id in the order.";
            }
            order.setId(orderRepository.getNextOrderId());
            if(accountService.getAccount(customer_id) == null) {
                return "Customer with id " + customer_id + " does not exist.";
            }
            for(Order o : order.getOrders()) {
                if(!validateSimpleOrder(o.getCustomerId(), (SimpleOrder) o)) {
                    return "Customer with id " + o.getCustomerId() + " does not exist.";
                }    
            }

            Account account = accountService.getAccount(customer_id);
            order.setCustomer(account);
            if(!order.place()) {
                return "some Customer does not have enough balance.";
            }

            for(Order o : order.getOrders()) {
                SimpleOrder simpleOrder = (SimpleOrder) o;
                simpleOrder.getCustomer().setBalance(simpleOrder.getCustomer().getBalance() - (o.getCost() + o.getShipmentFees()));
            }
            orderRepository.createOrder(customer_id, order);
            notificationService.makeOrderPlacedNotification(account.getUsername(), String.valueOf(order.getId()));
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

            return "Order " + order_id + " is cancelled.";
            
        }


}
