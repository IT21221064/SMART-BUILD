package com.example.procurement_staffapi.entity;

import com.example.procurement_staffapi.entity.OrderItemEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sm_orders")
public class SMOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderId;
    private String siteManager;
    private String siteName;
    private String status;
    private Date reqDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smOrder", fetch = FetchType.EAGER)
    private List<OrderItemEntity> items;

}
