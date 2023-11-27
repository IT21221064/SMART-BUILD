package com.example.procurement_staffapi.repository;

import com.example.procurement_staffapi.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryEntity,Long> {

}

