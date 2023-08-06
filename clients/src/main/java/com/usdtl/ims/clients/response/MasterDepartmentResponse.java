package com.usdtl.ims.clients.response;

import com.usdtl.ims.common.exceptions.constants.Category;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record MasterDepartmentResponse(
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
        String druClass,
        List<DepartmentResponse> departmentItems


) {
}
