package com.usdtl.ims.clients.responseRecord;

import com.usdtl.ims.common.exceptions.constants.Category;
import lombok.Builder;

@Builder
public record DepartmentMasterResponse(
        Integer department_id,
        Integer item_id,
        String item,
        String purchase_unit,
        String part_number,
        String recent_cn,
        String recent_vendor,
        String location,
        Integer total_quantity,
        String usage_level,
        Integer order_quantity,
        Double unit_price,
        Double total_price,
        String lot_number,
        String category,
        String comment
) {
}