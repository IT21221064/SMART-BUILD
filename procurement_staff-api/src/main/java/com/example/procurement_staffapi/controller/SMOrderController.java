package com.example.procurement_staffapi.controller;

import com.example.procurement_staffapi.model.SMOrder;
import com.example.procurement_staffapi.services.SMOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class SMOrderController {

    private final SMOrderService smOrderService;

    @Autowired
    public SMOrderController(SMOrderService smOrderService) {
        this.smOrderService = smOrderService;
    }

    @PostMapping("/smorders")
    public SMOrder createSMOrder(@RequestBody SMOrder smOrder) {
        return smOrderService.createSMOrder(smOrder);
    }

    @GetMapping("/smorders")
    public List<SMOrder> getAllSMOrders() {
        return smOrderService.getAllSMOrders();
    }

    @GetMapping("/smorders/{id}")
    public SMOrder getSMOrderById(@PathVariable Long id) {
        return smOrderService.getSMOrderById(id);
    }

    @PutMapping("/smorders/{id}")
    public SMOrder updateSMOrderStatus(@PathVariable Long id, @RequestBody SMOrder smOrder) {
        return smOrderService.updateSMOrderStatus(id, smOrder);
    }

    @DeleteMapping("/smorders/{id}")
    public void deleteSMOrder(@PathVariable Long id) {
        smOrderService.deleteSMOrder(id);
    }
}
