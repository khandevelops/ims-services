package com.usdtl.ims.users;

import com.usdtl.ims.common.constants.Category;

import java.util.Date;

public record MasterRequest(
        Integer id,
        String item,
        String manufacturer,
        String recent_cn,
        String part_number,
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
        String group,
        Integer minimum_quantity,
        Integer maximum_quantity,
        Date expiration_date,
        Date received_date,
        String drug_class
) {
}
