package com.example.procurement_staffapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private long id;
    private String itemName;
    private double price;
    private int quantity;
    private String seller;

    private double totPrice;
    public Object thenReturn(OrderItem orderItem){
        return null;
    }
}


