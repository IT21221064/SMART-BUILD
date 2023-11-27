package com.example.procurement_staffapi;

import com.example.procurement_staffapi.entity.OrderEntity;
import com.example.procurement_staffapi.model.Order;
import com.example.procurement_staffapi.repository.OrderRepository;
import com.example.procurement_staffapi.services.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void OrderService_CreateOrder_ReturnOrder() {
        Order order = Order.builder()
                .orderId("OR123")
                .siteName("ABCD")
                .supplierName("Nimal")
                .reqDate(new Date()) // Use the current date
                .siteAddress("123 Main St")
                .orderDescription("Sample Order")
                .totalPrice(100.0)
                .status("Pending")
                .build();

        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(order, orderEntity);

        when(orderRepository.save(Mockito.any(OrderEntity.class))).thenReturn(orderEntity);

        // Call the createOrder method in the service with an Order
        Order savedOrder = orderService.createOrder(order);

        // Assertions for the result
        assertNotNull(savedOrder);

    }
    @Test
    public void OrderService_GetAllOrders_ReturnResponseEntity() {
        // Create some mock OrderEntity objects
        OrderEntity orderEntity1 = OrderEntity.builder()
                .orderId("OR123")
                .siteName("ABCD")
                .supplierName("Nimal")
                .reqDate(new Date())
                .siteAddress("123 Main St")
                .orderDescription("Sample Order")
                .totalPrice(100.0)
                .status("Pending")
                .build();

        OrderEntity orderEntity2 = OrderEntity.builder()
                .orderId("OR124")
                .siteName("BCDE")
                .supplierName("Kamal")
                .reqDate(new Date())
                .siteAddress("58/A,Kaduwela")
                .orderDescription("Sample Order description")
                .totalPrice(1000.0)
                .status("Pending")
                .build();
        // Mock the behavior of your orderRepository to return the list of mock OrderEntities
        Mockito.when(orderRepository.findAll()).thenReturn(List.of(orderEntity1, orderEntity2));

        // Call the getAllOrders method in the service
        List<Order> orders = orderService.getAllOrders();

        // Verify the response
        assertEquals(2, orders.size()); // Assuming you expect 2 orders
    }

    @Test
    public void OrderService_GetOrderById_ReturnsOrder() {
        // Create a mock OrderEntity
        OrderEntity orderEntity = OrderEntity.builder()
                .id(1L) // Assuming the ID is 1
                .orderId("OR123")
                .siteName("ABCD")
                .supplierName("Nimal")
                .reqDate(new Date())
                .siteAddress("123 Main St")
                .orderDescription("Sample Order")
                .totalPrice(100.0)
                .status("Pending")
                .build();

        // Mock the behavior of your orderRepository to return the mock OrderEntity when findById is called with ID 1
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));

        // Call the getOrderById method in the service with the ID
        Order foundOrder = orderService.getOrderById(1L);

        // Assertions for the result
        assertNotNull(foundOrder);

        // Check all properties
        assertEquals(1L, foundOrder.getId());
        assertEquals("OR123", foundOrder.getOrderId());
        assertEquals("ABCD", foundOrder.getSiteName());
        assertEquals("Nimal", foundOrder.getSupplierName());
        assertEquals(orderEntity.getReqDate(), foundOrder.getReqDate()); // Assuming you have appropriate getters for Date
        assertEquals("123 Main St", foundOrder.getSiteAddress());
        assertEquals("Sample Order", foundOrder.getOrderDescription());
        assertEquals(100.0, foundOrder.getTotalPrice());
        assertEquals("Pending", foundOrder.getStatus());
    }



}
