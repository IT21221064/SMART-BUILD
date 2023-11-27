package com.example.procurement_staffapi.services;


import com.example.procurement_staffapi.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(Long orderId);

    Order updateOrderStatus(Long id, Order order);
}
