package com.example.procurement_staffapi;

import com.example.procurement_staffapi.entity.OrderEntity;
import com.example.procurement_staffapi.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class OrderRepositoryTests {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void OrderRepository_SaveAndRetrieve_ReturnSavedOrder() {
        // Arrange
        OrderEntity orderEntity = OrderEntity.builder()
                .orderId("OR123")
                .siteName("ABCD")
                .supplierName("Nimal")
                .reqDate(new Date()) // Use the current date
                .siteAddress("123 Main St")
                .orderDescription("Sample Order")
                .totalPrice(100.0)
                .status("Pending")
                .build();

        // Act: Save the order
        OrderEntity savedOrder = orderRepository.save(orderEntity);

        // Assert: Check if the saved order is not null
        assertNotNull(savedOrder);

        // Assert: Check if the saved order matches the original order
        assertEquals(orderEntity.getOrderId(), savedOrder.getOrderId());
        assertEquals(orderEntity.getSiteName(), savedOrder.getSiteName());
        assertEquals(orderEntity.getSupplierName(), savedOrder.getSupplierName());
        assertEquals(orderEntity.getReqDate(), savedOrder.getReqDate());
        assertEquals(orderEntity.getSiteAddress(), savedOrder.getSiteAddress());
        assertEquals(orderEntity.getOrderDescription(), savedOrder.getOrderDescription());
        assertEquals(orderEntity.getTotalPrice(), savedOrder.getTotalPrice());
        assertEquals(orderEntity.getStatus(), savedOrder.getStatus());
    }

    @Test
    public void Order_GetALL_ReturnMoreThanOneOrder() {
        // Create and save two order entities
        OrderEntity order1 = OrderEntity.builder()
                .orderId("OR123")
                .siteName("ABCD")
                .supplierName("Nimal")
                .reqDate(new Date()) // Use the current date
                .siteAddress("123 Main St")
                .orderDescription("Sample Order")
                .totalPrice(100.0)
                .status("Pending")
                .build();

        OrderEntity order2 = OrderEntity.builder()
                .orderId("OR124")
                .siteName("BCDE")
                .supplierName("Kamal")
                .reqDate(new Date()) // Use the current date
                .siteAddress("58/A, Kaduwela")
                .orderDescription("Sample Order description")
                .totalPrice(1000.0)
                .status("Pending")
                .build();

        orderRepository.save(order1);
        orderRepository.save(order2);

        // Retrieve the list of orders
        List<OrderEntity> orderList = orderRepository.findAll();

        // Assert that the list contains more than one order
        Assertions.assertTrue(orderList.size() > 1);
    }


    @Test
    public void Order_FindById_ReturnOrder() {
        OrderEntity order = OrderEntity.builder()
                .orderId("OR123")
                .siteName("ABCD")
                .supplierName("Nimal")
                .reqDate(new Date()) // Use the current date
                .siteAddress("123 Main St")
                .orderDescription("Sample Order")
                .totalPrice(100.0)
                .status("Pending")
                .build();
        orderRepository.save(order);

        // Retrieve the order by ID
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(order.getId());

        // Assert that the order was found
        Assertions.assertTrue(optionalOrderEntity.isPresent(), "Order not found by ID");

        // Get the actual order entity from the Optional
        OrderEntity foundOrder = optionalOrderEntity.get();

        // Assert that the found order matches the saved order
        Assertions.assertEquals(order.getId(), foundOrder.getId());
        Assertions.assertEquals(order.getOrderId(), foundOrder.getOrderId());
        Assertions.assertEquals(order.getSiteName(), foundOrder.getSiteName());
        Assertions.assertEquals(order.getSupplierName(), foundOrder.getSupplierName());
        Assertions.assertEquals(order.getReqDate(), foundOrder.getReqDate());
        Assertions.assertEquals(order.getSiteAddress(), foundOrder.getSiteAddress());
        Assertions.assertEquals(order.getOrderDescription(), foundOrder.getOrderDescription());
        Assertions.assertEquals(order.getTotalPrice(), foundOrder.getTotalPrice());
        Assertions.assertEquals(order.getStatus(), foundOrder.getStatus());
    }


}
