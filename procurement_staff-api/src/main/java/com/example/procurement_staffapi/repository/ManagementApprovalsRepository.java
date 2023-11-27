package com.example.procurement_staffapi.repository;

import com.example.procurement_staffapi.entity.ManagementApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementApprovalsRepository extends JpaRepository<ManagementApprovalEntity,Long> {

}