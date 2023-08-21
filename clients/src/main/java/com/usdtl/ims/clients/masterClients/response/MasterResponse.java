package com.usdtl.ims.clients.masterClients.response;

import lombok.Builder;

@Builder
public record MasterResponse(
        Integer id,
        String item,
        String manufacturer,
        String partNumber,
        String recentCN,
        String recentVendor,
        String fisherCN,
        String vwrCN,
        String labSourceCN,
        String otherCN,
        String purchaseUnit,
        Double unitPrice,
        String category,
        String comment,
        String itemType,
        String itemGroup,
        String drugClass
) {
}
