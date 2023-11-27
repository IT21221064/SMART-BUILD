package com.example.procurement_staffapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMOrder {
    private long id;
    private String orderId;
    private String siteManager;
    private String siteName;
    private String status;
    private Date reqDate;
    private List<OrderItem> items;
    public Object thenReturn(SMOrder smOrder){
        return null;
    }
}
