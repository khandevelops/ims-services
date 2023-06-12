package com.usdtl.ims.clients.responseRecord;

import com.usdtl.ims.common.exceptions.constants.Category;
import lombok.Builder;

@Builder
public record MasterDepartmentResponse(
        Integer department_item_id,
        Integer master_item_id,
        String master_item,
        String purchase_unit,
        String part_number,
        String recent_cn,
        String recent_vendor,
        String location,
        Integer total_quantity,
        String usage_level,
        Integer min_quantity,
        Integer max_quantity,
        Integer order_quantity,
        Double unit_price,
        Double total_price,
        String lot_number,
        Category category,
        String comment
) {
}
