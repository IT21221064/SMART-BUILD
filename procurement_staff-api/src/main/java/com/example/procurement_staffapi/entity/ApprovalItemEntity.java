package com.example.procurement_staffapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "approval_items")
public class ApprovalItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String itemName;
    private double price;
    private int quantity;
    private String seller;
    private double totPrice;
    @ManyToOne // Many order items belong to one SMOrder
    @JoinColumn(name = "sm_order_id") // Define the foreign key column name
    private ManagementApprovalEntity managementApproval; // This field represents the relationship
}