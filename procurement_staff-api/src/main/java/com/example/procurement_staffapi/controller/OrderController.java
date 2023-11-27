package com.example.procurement_staffapi.controller;

import com.example.procurement_staffapi.model.Order;
import com.example.procurement_staffapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }


    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{Id}")
    public Order getOrderById(@PathVariable Long Id) {
        return orderService.getOrderById(Id);
    }

    @PutMapping("/order/{Id}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long Id, @RequestBody Order order) {
        order = orderService.updateOrderStatus(Id, order);
        return ResponseEntity.ok(order);
    }

}
