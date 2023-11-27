package com.example.procurement_staffapi.services;

import com.example.procurement_staffapi.entity.OrderEntity;
import com.example.procurement_staffapi.model.Order;
import com.example.procurement_staffapi.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(order, orderEntity);
        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        List<Order> orders = orderEntities
                .stream()
                .map(ord -> new Order(
                        ord.getId(),
                        ord.getOrderId(),
                        ord.getSiteName(),
                        ord.getSupplierName(),
                        ord.getReqDate(),
                        ord.getSiteAddress(),
                        ord.getOrderDescription(),
                        ord.getTotalPrice(),
                        ord.getStatus()
                ))
                .collect(Collectors.toList());
        return orders;
    }

    @Override
    public Order getOrderById(Long Id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(Id);
        if (orderEntity.isPresent()) {
            OrderEntity order = orderEntity.get();
            return new Order(
                    order.getId(),
                    order.getOrderId(),
                    order.getSiteName(),
                    order.getSupplierName(),
                    order.getReqDate(),
                    order.getSiteAddress(),
                    order.getOrderDescription(),
                    order.getTotalPrice(),
                    order.getStatus()
            );
        }
        // If the order is not found, you can return null or throw an exception as needed.
        return null;
    }

    @Override
    public Order updateOrderStatus(Long id, Order order) {
        OrderEntity orderEntity=orderRepository.findById(id).get();
        orderEntity.setStatus(order.getStatus());
        orderRepository.save(orderEntity);
        return order;
    }
}
