package com.usdtl.ims.clients;

import com.usdtl.ims.common.constants.Category;
import lombok.Builder;

@Builder
public record MasterItemRequest(
        Integer id,
        String item,
        String manufacturer,
        String part_number,
        String recent_cn,
        String recent_vendor,
        String fisher_cn,
        String vwr_cn,
        String lab_source_cn,
        String next_advance_cn,
        String purchase_unit,
        Double average_unit_price,
        Category category,
        String comment,
        String type,
        String group
) {
}
