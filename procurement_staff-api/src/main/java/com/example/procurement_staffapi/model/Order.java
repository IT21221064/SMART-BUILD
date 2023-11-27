package com.example.procurement_staffapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;
    private String orderId;
    private String siteName;
    private String supplierName;
    private Date reqDate;
    private String siteAddress;
    private String orderDescription;
    private Double totalPrice;
    private String status;

    public Object thenReturn(Order order) {
        return null;
    }
}
