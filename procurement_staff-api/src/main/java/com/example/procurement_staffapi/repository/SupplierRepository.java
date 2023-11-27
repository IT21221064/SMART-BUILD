package com.example.procurement_staffapi.repository;

import com.example.procurement_staffapi.entity.OrderEntity;
import com.example.procurement_staffapi.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity,Long> {
}
