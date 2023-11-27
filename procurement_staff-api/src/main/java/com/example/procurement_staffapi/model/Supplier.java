package com.example.procurement_staffapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

    private long id;
    private String supName;
    private String supPhone;
    private String supAddress;

    public Object thenReturn(Supplier supplier) {
        return null;
    }

}
