package com.usdtl.ims.requestMaster.request;

import com.usdtl.ims.common.constants.Department;
import com.usdtl.ims.common.constants.Status;
import lombok.Builder;

import java.util.Date;

@Builder
public record RequestMasterTransformedAdminRequest(
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
