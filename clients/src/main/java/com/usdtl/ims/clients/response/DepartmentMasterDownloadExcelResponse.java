package com.usdtl.ims.clients.response;

import com.usdtl.ims.common.constants.Category;
import lombok.Builder;

import java.util.Date;

@Builder
public record DepartmentMasterDownloadExcelResponse(
        Integer department_item_id,
        Integer master_item_id,
        String item,
        String purchase_unit,
        String part_number,
        String recent_cn,
        Integer total_quantity,
        Integer quantity,
        String usage_level,
        Integer min_quantity,
        Integer max_quantity,
        Integer order_quantity,
        Double unit_price,
        Double total_price,
        String comment,
        String location,
        Category category,
        String lot_number,
        Date expiration_date,
        Date received_date
) {
}
