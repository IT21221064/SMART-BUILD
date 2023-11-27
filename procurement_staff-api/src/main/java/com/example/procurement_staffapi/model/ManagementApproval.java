package com.example.procurement_staffapi.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagementApproval {
    private long id;
    private String orderId;
    private String siteManager;
    private String siteName;
    private String status;
    private Date reqDate;
    private List<ApprovalItem> items;

    public Object thenReturn(ManagementApproval managementApproval) {
        return null;
    }
}
