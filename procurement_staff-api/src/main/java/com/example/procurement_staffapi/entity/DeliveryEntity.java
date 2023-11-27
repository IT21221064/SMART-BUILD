package com.example.procurement_staffapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "deliveries")
public class DeliveryEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String orderId;
    private String siteName;
    private String supplierName;
    private String deliveryNote;
    private Date deliveryDate;
    private String siteAddress;
    private Double totalPrice;
    private String phone;
}
