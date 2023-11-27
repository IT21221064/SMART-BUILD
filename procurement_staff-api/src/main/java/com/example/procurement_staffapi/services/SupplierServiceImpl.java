package com.example.procurement_staffapi.services;

import com.example.procurement_staffapi.entity.OrderEntity;
import com.example.procurement_staffapi.entity.SupplierEntity;
import com.example.procurement_staffapi.model.Order;
import com.example.procurement_staffapi.model.Supplier;
import com.example.procurement_staffapi.repository.OrderRepository;
import com.example.procurement_staffapi.repository.SupplierRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService{

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    @Override
    public Supplier createSupplier(Supplier supplier) {
        SupplierEntity supplierEntity = new SupplierEntity();
        BeanUtils.copyProperties(supplier, supplierEntity);
        supplierRepository.save(supplierEntity);
        return supplier;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        List<SupplierEntity> supplierEntities = supplierRepository.findAll();
        List<Supplier> suppliers = supplierEntities
                .stream()
                .map(sup -> new Supplier(
                        sup.getId(),
                        sup.getSupName(),
                        sup.getSupPhone(),
                        sup.getSupAddress()
                ))
                .collect(Collectors.toList());
        return suppliers;
    }
}
