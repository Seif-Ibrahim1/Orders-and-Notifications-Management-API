package com.example.ordersmanagement.order;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/customers/{customer_id}/orders/")
public class OrderController {
    
        final private OrderService orderService;
    
        public OrderController(OrderService orderService) {
            this.orderService = orderService;
        }

        @GetMapping
        public ArrayList<Order> getOrders(@RequestParam int customer_id) {
            return orderService.getOrders(customer_id);
        }

        @PostMapping
        public String createOrder(@RequestParam int customer_id, @RequestBody Order order) {
            return orderService.createOrder(customer_id, order);
        }

        @GetMapping("/{order_id}")
        public String getOrder(@RequestParam int customer_id, @RequestParam int order_id) {
            return orderService.getOrder(customer_id, order_id);
        }

        @DeleteMapping("/{order_id}")
        public String deleteOrder(@RequestParam int customer_id, @RequestParam int order_id) {
            return orderService.cancelOrder(customer_id, order_id);
        }
}
