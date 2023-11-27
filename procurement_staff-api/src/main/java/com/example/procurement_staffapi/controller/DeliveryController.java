package com.example.procurement_staffapi.controller;


import com.example.procurement_staffapi.model.Delivery;
import com.example.procurement_staffapi.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class DeliveryController {
    @Autowired
    private final DeliveryService deliveryService;
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/deliveries")
    public List<Delivery> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/delivery/{id}")
    public Delivery getDeliveryById(@PathVariable Long id)
    {
        return deliveryService.getDeliveryById(id);
    }
}
