package com.example.procurement_staffapi.controller;

import com.example.procurement_staffapi.model.Order;
import com.example.procurement_staffapi.model.Supplier;
import com.example.procurement_staffapi.services.OrderService;
import com.example.procurement_staffapi.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class SupplierController {
    @Autowired
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/suppliers")
    public Supplier createSupplier(@RequestBody Supplier supplier){
        return supplierService.createSupplier(supplier);
    }
    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }
}
