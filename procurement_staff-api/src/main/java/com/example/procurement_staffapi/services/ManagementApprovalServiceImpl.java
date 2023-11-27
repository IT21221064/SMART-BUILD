package com.example.procurement_staffapi.services;


import com.example.procurement_staffapi.entity.ApprovalItemEntity;
import com.example.procurement_staffapi.entity.ManagementApprovalEntity;
import com.example.procurement_staffapi.model.ApprovalItem;
import com.example.procurement_staffapi.model.ManagementApproval;
import com.example.procurement_staffapi.repository.ManagementApprovalsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManagementApprovalServiceImpl implements ManagementApprovelService {
    private ManagementApprovalsRepository managementApprovalsRepository;

    public ManagementApprovalServiceImpl(ManagementApprovalsRepository managementApprovalsRepository) {
        this.managementApprovalsRepository = managementApprovalsRepository;
    }


    @Override
    public ManagementApproval createApprovals(ManagementApproval managementApproval) {
        ManagementApprovalEntity managementApprovalEntity = new  ManagementApprovalEntity ();
        BeanUtils.copyProperties(managementApproval, managementApprovalEntity);

        // Map the items
        if (managementApproval.getItems() != null) {
            List<ApprovalItemEntity> approvalItemEntities = managementApproval.getItems().stream()
                    .map(approvalItem -> {
                        ApprovalItemEntity approvalItemEntity = new ApprovalItemEntity();
                        BeanUtils.copyProperties(approvalItem, approvalItemEntity);

                        // Set the SMOrderEntity for the ApprovalItemEntity
                        approvalItemEntity.setManagementApproval(managementApprovalEntity);

                        return approvalItemEntity;
                    })
                    .collect(Collectors.toList());
            managementApprovalEntity.setItems(approvalItemEntities);
        }


        managementApprovalsRepository.save(managementApprovalEntity);
        return managementApproval;
    }

    @Override
    public List<ManagementApproval> getAllApprovals() {
        List<ManagementApprovalEntity> managementApprovalEntities = managementApprovalsRepository.findAll();
        return managementApprovalEntities.stream()
                .map(this::mapToManagementApprovals)
                .collect(Collectors.toList());
    }
    private ManagementApproval mapToManagementApprovals(ManagementApprovalEntity managementApprovalEntity) {
        ManagementApproval managementApproval = new ManagementApproval();
        BeanUtils.copyProperties(managementApprovalEntity, managementApproval);

        // Map the items
        if (managementApprovalEntity.getItems() != null) {
            List<ApprovalItem> approvalItems = managementApprovalEntity.getItems().stream()
                    .map(approvalItemEntity -> {
                        ApprovalItem approvalItem = new ApprovalItem();
                        BeanUtils.copyProperties(approvalItemEntity, approvalItem);
                        return approvalItem;
                    })
                    .collect(Collectors.toList());
            managementApproval.setItems(approvalItems);
        }

        return managementApproval;
    }

    @Override
    public ManagementApproval getApprovalById(Long id) {
        Optional<ManagementApprovalEntity> managementApprovalEntityOptional = managementApprovalsRepository.findById(id);

        if (managementApprovalEntityOptional.isPresent()) {
            ManagementApprovalEntity managementApprovalEntity = managementApprovalEntityOptional.get();

            ManagementApproval managementApproval = new ManagementApproval();
            BeanUtils.copyProperties(managementApprovalEntity, managementApproval);

            if (managementApprovalEntity.getItems() != null) {
                List<ApprovalItem> approvalItems = managementApprovalEntity.getItems().stream()
                        .map(approvalItemEntity -> {
                            ApprovalItem approvalItem = new ApprovalItem();
                            BeanUtils.copyProperties(approvalItemEntity, approvalItem);
                            return approvalItem;
                        })
                        .collect(Collectors.toList());

                managementApproval.setItems(approvalItems);
            }

            return managementApproval;
        } else {
            // Handle the case where the specified ID doesn't exist
            return null;
        }
    }

    @Override
    public ManagementApproval updateApprovalStatus(Long id, ManagementApproval managementApproval) {
        ManagementApprovalEntity managementApprovalEntity = managementApprovalsRepository.findById(id).get();
        managementApprovalEntity.setStatus(managementApproval.getStatus());
        managementApprovalsRepository.save(managementApprovalEntity);
        return managementApproval;
    }
}