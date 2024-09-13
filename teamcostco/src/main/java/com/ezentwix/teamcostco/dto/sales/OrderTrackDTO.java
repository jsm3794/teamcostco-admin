package com.ezentwix.teamcostco.dto.sales;

import lombok.Data;

@Data
public class OrderTrackDTO {
    private int pendingCount;
    private int packedCount;
    private int dispatchedCount;
    private int invoiceCount;

}
