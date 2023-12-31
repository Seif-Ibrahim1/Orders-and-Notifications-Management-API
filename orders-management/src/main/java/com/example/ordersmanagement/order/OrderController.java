package com.example.ordersmanagement.order;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/customers/{customer_id}/orders")
public class OrderController {
    
        private final OrderService orderService;
    
        public OrderController(OrderService orderService) {
            this.orderService = orderService;
        }

        @GetMapping
        public ArrayList<Order> getOrders(@PathVariable int customer_id) {
            return orderService.getOrders(customer_id);
        }

        @PostMapping("/place_simple_order")
        public String createOrder(@PathVariable int customer_id, @RequestBody SimpleOrder order) {
            return orderService.createSimpleOrder(customer_id, order);
        }

        @PostMapping("/place_compound_order")
        public String createOrder(@PathVariable int customer_id, @RequestBody CompoundOrder order) {
            return orderService.createCompoundOrder(customer_id, order);
        }

        @GetMapping("/{order_id}")
        public Order getOrder(@PathVariable int customer_id, @PathVariable int order_id) {
            return orderService.getOrder(customer_id, order_id);
        }
    
        @DeleteMapping("/{order_id}")
        public String deleteOrder(@PathVariable int customer_id, @PathVariable int order_id) {
            return orderService.cancelOrder(customer_id, order_id);
        }

        @PutMapping("/{order_id}/mark_as_shipped")
        public String markAsShipped(@PathVariable int customer_id, @PathVariable int order_id) {
            return orderService.markAsShipped(customer_id, order_id);
        }


}
