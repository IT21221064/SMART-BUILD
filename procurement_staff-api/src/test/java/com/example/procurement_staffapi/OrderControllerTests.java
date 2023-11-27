package com.example.procurement_staffapi;

import com.example.procurement_staffapi.controller.OrderController;
import com.example.procurement_staffapi.entity.OrderEntity;
import com.example.procurement_staffapi.model.Order;
import com.example.procurement_staffapi.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers; // Import the missing classes


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class OrderControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void OrderController_Create_ReturnCreated() throws Exception {
        // Create a sample Order object with properties
        Order order = new Order();
        order.setOrderId("12345");
        order.setSiteName("Sample Site");
        order.setSupplierName("Sample Supplier");
        order.setReqDate(new Date());
        order.setSiteAddress("Sample Address");
        order.setOrderDescription("Sample Description");
        order.setTotalPrice(100.0);
        order.setStatus("Pending");

        // Mock the behavior of the orderService.createOrder method
        when(orderService.createOrder(ArgumentMatchers.any(Order.class))).thenReturn(order);

        // Perform a POST request to create an order and assert the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    public void OrderController_GetAllOrders_ReturnResponseEntity() throws Exception {
        // Create a list of sample orders
        List<Order> sampleOrders = new ArrayList<>();

        // Create and add sample orders to the list
        Order order1 = new Order();
        order1.setOrderId("1");
        order1.setSiteName("Sample Site 1");
        order1.setSupplierName("Sample Supplier 1");
        order1.setReqDate(new Date());
        order1.setSiteAddress("Sample Address 1");
        order1.setOrderDescription("Sample Description 1");
        order1.setTotalPrice(100.0);
        order1.setStatus("Pending");

        Order order2 = new Order();
        order2.setOrderId("2");
        order2.setSiteName("Sample Site 2");
        order2.setSupplierName("Sample Supplier 2");
        order2.setReqDate(new Date());
        order2.setSiteAddress("Sample Address 2");
        order2.setOrderDescription("Sample Description 2");
        order2.setTotalPrice(150.0);
        order2.setStatus("Completed");

        sampleOrders.add(order1);
        sampleOrders.add(order2);

        // Mock the behavior of the orderService.getAllOrders method
        when(orderService.getAllOrders()).thenReturn(sampleOrders);

        // Perform a GET request to retrieve all orders
        mockMvc.perform(get("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expect a 200 OK status
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(sampleOrders.size()))); // Expect the response to be a JSON array with the same size as the sampleOrders list
    }

    @Test
    public void OrderController_GetOrderById_ReturnOrder() throws Exception {
        long Id = 1L; // Use a valid ID for the test
        Order orderEntity = new Order();
        orderEntity.setOrderId("OR1234");
        orderEntity.setSiteName("Sample Site");
        orderEntity.setSupplierName("Sample Supplier");
        orderEntity.setReqDate(new Date());
        orderEntity.setSiteAddress("Sample Address");
        orderEntity.setOrderDescription("Sample Description");
        orderEntity.setTotalPrice(100.0);
        orderEntity.setStatus("Pending");

        // Mock the behavior of the orderService.getOrderById method to return the sample order
        when(orderService.getOrderById(Id)).thenReturn(orderEntity);

        ResultActions response = mockMvc.perform(get("/api/v1/order/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value("OR1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.siteName").value("Sample Site"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.supplierName").value("Sample Supplier"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.siteAddress").value("Sample Address"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderDescription").value("Sample Description"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value(100.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Pending"));
    }



}
