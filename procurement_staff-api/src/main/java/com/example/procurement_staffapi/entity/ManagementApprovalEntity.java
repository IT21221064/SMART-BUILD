package com.example.procurement_staffapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Approvals")
public class ManagementApprovalEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String orderId;
    private String siteManager;
    private String siteName;
    private String status;
    private Date reqDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "managementApproval", fetch = FetchType.EAGER)
    private List<ApprovalItemEntity> items;


}
