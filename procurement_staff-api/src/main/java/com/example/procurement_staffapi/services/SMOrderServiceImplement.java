package com.example.procurement_staffapi.services;

import com.example.procurement_staffapi.entity.OrderEntity;
import com.example.procurement_staffapi.entity.OrderItemEntity;
import com.example.procurement_staffapi.entity.SMOrderEntity;
import com.example.procurement_staffapi.model.Order;
import com.example.procurement_staffapi.model.OrderItem;
import com.example.procurement_staffapi.model.SMOrder;
import com.example.procurement_staffapi.repository.OrderRepository;
import com.example.procurement_staffapi.repository.SMOrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SMOrderServiceImplement implements SMOrderService {
    private final SMOrderRepository smOrderRepository;

    public SMOrderServiceImplement(SMOrderRepository smOrderRepository) {
        this.smOrderRepository = smOrderRepository;
    }


    @Override

    public SMOrder createSMOrder(SMOrder smOrder) {
        SMOrderEntity smOrderEntity = new SMOrderEntity();
        BeanUtils.copyProperties(smOrder, smOrderEntity);

        // Map the items
        if (smOrder.getItems() != null) {
            List<OrderItemEntity> orderItemEntities = smOrder.getItems().stream()
                    .map(orderItem -> {
                        OrderItemEntity orderItemEntity = new OrderItemEntity();
                        BeanUtils.copyProperties(orderItem, orderItemEntity);

                        // Set the SMOrderEntity for the OrderItemEntity
                        orderItemEntity.setSmOrder(smOrderEntity);

                        return orderItemEntity;
                    })
                    .collect(Collectors.toList());
            smOrderEntity.setItems(orderItemEntities);
        }

        smOrderRepository.save(smOrderEntity);
        return smOrder;
    }


    @Override
    public List<SMOrder> getAllSMOrders() {
        List<SMOrderEntity> smOrderEntities = smOrderRepository.findAll();
        return smOrderEntities.stream()
                .map(this::mapToSMOrder)
                .collect(Collectors.toList());
    }
    private SMOrder mapToSMOrder(SMOrderEntity smOrderEntity) {
        SMOrder smOrder = new SMOrder();
        BeanUtils.copyProperties(smOrderEntity, smOrder);

        // Map the items
        if (smOrderEntity.getItems() != null) {
            List<OrderItem> orderItems = smOrderEntity.getItems().stream()
                    .map(orderItemEntity -> {
                        OrderItem orderItem = new OrderItem();
                        BeanUtils.copyProperties(orderItemEntity, orderItem);
                        return orderItem;
                    })
                    .collect(Collectors.toList());
            smOrder.setItems(orderItems);
        }

        return smOrder;
    }


    @Override
    public SMOrder getSMOrderById(Long id) {
        Optional<SMOrderEntity> smOrderEntityOptional = smOrderRepository.findById(id);

        if (smOrderEntityOptional.isPresent()) {
            SMOrderEntity smOrderEntity = smOrderEntityOptional.get();

            SMOrder smOrder = new SMOrder();
            BeanUtils.copyProperties(smOrderEntity, smOrder);

            if (smOrderEntity.getItems() != null) {
                List<OrderItem> orderItems = smOrderEntity.getItems().stream()
                        .map(orderItemEntity -> {
                            OrderItem orderItem = new OrderItem();
                            BeanUtils.copyProperties(orderItemEntity, orderItem);
                            return orderItem;
                        })
                        .collect(Collectors.toList());

                smOrder.setItems(orderItems);
            }

            return smOrder;
        } else {
            // Handle the case where the specified ID doesn't exist
            return null;
        }
    }


    @Override
    public SMOrder updateSMOrderStatus(Long id, SMOrder smOrder) {
        SMOrderEntity smorderEntity=smOrderRepository.findById(id).get();
        smorderEntity.setStatus(smOrder.getStatus());
        smOrderRepository.save(smorderEntity);
        return smOrder;
    }

    @Override
    public void deleteSMOrder(Long id) {

    }
}
