package com.usdtl.ims.departmentMaster.requestMaster.request;

import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.ims.common.exceptions.constants.Status;
import lombok.Builder;

import java.util.Date;

@Builder
public record RequestMasterTransformedAdminResponse(
        String item,
        Integer request_item_id,
        Integer master_item_id,
        String recent_cn,
        Department department,
        Status status,
        Integer order_quantity,
        Integer quantity,
        Date time_requested,
        Date time_update,
        String detail,
        String custom_text
) {
}
