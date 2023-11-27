package com.example.procurement_staffapi.services;

import com.example.procurement_staffapi.model.Delivery;

import java.util.List;

public interface DeliveryService {


    List<Delivery> getAllDeliveries();

    Delivery getDeliveryById(Long id);
}
