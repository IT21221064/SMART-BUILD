package com.example.procurement_staffapi.services;

import com.example.procurement_staffapi.model.SMOrder;

import java.util.List;

public interface SMOrderService {
    SMOrder createSMOrder(SMOrder smOrder);
    List<SMOrder> getAllSMOrders();
    SMOrder getSMOrderById(Long id);
    SMOrder updateSMOrderStatus(Long id, SMOrder smOrder);
    void deleteSMOrder(Long id);
}
