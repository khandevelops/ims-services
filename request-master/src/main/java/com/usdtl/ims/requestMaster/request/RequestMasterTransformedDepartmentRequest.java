package com.usdtl.ims.requestMaster.request;

import com.usdtl.ims.common.constants.Status;
import lombok.Builder;

import java.util.Date;

@Builder
public record RequestMasterTransformedDepartmentRequest(
        String item,
        Integer request_item_id,
        Integer master_item_id,
        String recent_cn,
        String purchase_unit,
        String part_number,
        String detail,
        Status status,
        Integer quantity,
        Date time_requested,
        Date time_update,
        String custom_text
) {
}
