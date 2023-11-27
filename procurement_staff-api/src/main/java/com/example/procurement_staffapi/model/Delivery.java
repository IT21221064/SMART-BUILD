package com.example.procurement_staffapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Delivery {
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
