package com.usdtl.ims.requestMaster.request;

import com.usdtl.ims.common.constants.Category;

public record MasterRequest(
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
        String comments,
        String type,
        String group
) {
}
