package com.usdtl.ims.requestDepartment.request;

import lombok.Builder;

@Builder
public record RequestDepartmentResponse (
        String item,
        Integer request_item_id,
        Integer master_item_id,
        String recent_cn,
        String purchase_unit,
        String part_number,
        String comment
) {

}
