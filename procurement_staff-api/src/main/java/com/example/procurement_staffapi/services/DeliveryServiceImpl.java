package com.example.procurement_staffapi.services;


import com.example.procurement_staffapi.entity.DeliveryEntity;
import com.example.procurement_staffapi.model.Delivery;
import com.example.procurement_staffapi.repository.DeliveryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService{
    private DeliveryRepository deliveryRepository;
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }




    @Override
    public List<Delivery> getAllDeliveries() {
        List<DeliveryEntity>deliveryEntities
                =deliveryRepository.findAll();

        List<Delivery> deliveries = deliveryEntities.stream().map(del-> new Delivery(
                del.getId(),
                del.getOrderId(),
                del.getSiteName(),
                del.getSupplierName(),
                del.getDeliveryNote(),
                del.getDeliveryDate(),
                del.getSiteAddress(),
                del.getTotalPrice(),
                del.getPhone()
        )).collect(Collectors.toList());

        return deliveries;
    }

    @Override
    public Delivery getDeliveryById(Long id) {


        Optional<DeliveryEntity> deliveryEntity = deliveryRepository.findById(id);
        if (deliveryEntity.isPresent()) {
            DeliveryEntity delivery = deliveryEntity.get();
            return new Delivery(
                    delivery.getId(),
                    delivery.getOrderId(),
                    delivery.getSiteName(),
                    delivery.getSupplierName(),
                    delivery.getDeliveryNote(),
                    delivery.getDeliveryDate(),
                    delivery.getSiteAddress(),
                    delivery.getTotalPrice(),
                    delivery.getPhone()
            );
        }
        // If the order is not found, you can return null or throw an exception as needed.
        return null;
    }


}
