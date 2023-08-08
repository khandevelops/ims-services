package com.usdtl.ims.clients;

import com.usdtl.ims.common.exceptions.constants.Category;
import lombok.Builder;

@Builder
public record MasterItemRequest(
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
        String druClass
) {
}
