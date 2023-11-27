package com.example.procurement_staffapi.controller;


import com.example.procurement_staffapi.model.ManagementApproval;
import com.example.procurement_staffapi.services.ManagementApprovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ManagementApprovalController {
    private final ManagementApprovelService managementApprovelService;
    @Autowired
    public ManagementApprovalController(ManagementApprovelService managementApprovelService) {
        this.managementApprovelService = managementApprovelService;
    }
    @PostMapping("/approvals")
    public ManagementApproval createApprovals(@RequestBody ManagementApproval managementApproval) {
        return managementApprovelService.createApprovals(managementApproval);
    }

    @GetMapping("/approvals")
    public List<ManagementApproval> getAllApprovals() {
        return managementApprovelService.getAllApprovals();
    }

    @GetMapping("/approvals/{id}")
    public ManagementApproval getApprovalById(@PathVariable Long id) {
        return managementApprovelService.getApprovalById(id);
    }

    @PutMapping("/approvals/{id}")
    public ManagementApproval updateApprovalStatus(@PathVariable Long id, @RequestBody ManagementApproval managementApproval) {
        return managementApprovelService.updateApprovalStatus(id, managementApproval);
    }


}


