package com.example.procurement_staffapi.services;

import com.example.procurement_staffapi.model.Order;
import com.example.procurement_staffapi.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierService {

    Supplier createSupplier(Supplier supplier);

    List<Supplier> getAllSuppliers();
}
