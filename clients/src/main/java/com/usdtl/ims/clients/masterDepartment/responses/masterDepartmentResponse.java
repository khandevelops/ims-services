package com.usdtl.ims.clients.masterDepartment.responses;

import com.usdtl.ims.clients.departments.common.response.DepartmentResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record masterDepartmentResponse(
        String item,
        String purchaseUnit,
        String partNumber,
        String recentCN,
        String recentVendor,
        String drugClass,
        Double unitPrice,
        String comment,
        String category,
        Integer totalQuantity,
        Integer orderQuantity,
        Double totalPrice,
        List<DepartmentResponse> departmentItems
) {
}
