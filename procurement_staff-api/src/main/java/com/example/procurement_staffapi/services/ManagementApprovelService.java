package com.example.procurement_staffapi.services;

import com.example.procurement_staffapi.model.ManagementApproval;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManagementApprovelService {
    ManagementApproval createApprovals(ManagementApproval managementApproval);

    List<ManagementApproval> getAllApprovals();

    ManagementApproval getApprovalById(Long id);

    ManagementApproval updateApprovalStatus(Long id, ManagementApproval managementApproval);
}