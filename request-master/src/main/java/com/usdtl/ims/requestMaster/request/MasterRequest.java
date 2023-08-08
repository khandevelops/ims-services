package com.usdtl.ims.requestMaster.request;

import com.usdtl.ims.common.exceptions.constants.Category;

public record MasterRequest(
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
