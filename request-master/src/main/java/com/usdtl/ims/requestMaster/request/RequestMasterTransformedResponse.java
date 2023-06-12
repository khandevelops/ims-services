package com.usdtl.ims.requestMaster.request;

import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.ims.common.exceptions.constants.Status;
import lombok.Builder;

import java.util.Date;

@Builder
public record RequestMasterTransformedResponse(
        String item,
        Integer request_item_id,
        Integer master_item_id,
        String recent_cn,
        String purchase_unit,
        Department department,
        Status status,
        Integer quantity,
        Date time_requested,
        Date time_update,
        String detail,
        String custom_text
) {
}
