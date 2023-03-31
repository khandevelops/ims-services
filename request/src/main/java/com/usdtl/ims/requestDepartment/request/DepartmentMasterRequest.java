package com.usdtl.ims.requestDepartment.request;


public record DepartmentMasterRequest(
        Integer id,
        String location,
        Integer quantity,
        Integer min_quantity,
        Integer max_quantity,
        String usage_level,
        MasterRequest masterItem
) {
}
