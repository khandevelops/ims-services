package com.usdtl.ims.departmentMaster.qualityMaster;

import lombok.Builder;

import java.util.Date;

@Builder
public record QualityMasterRequest(
        Integer id,
        Integer item_id,
        String location,
        Integer quantity,
        Integer min_quantity,
        Integer max_quantity,
        String usage_level,
        String lot_number,
        Date expiration_date,
        Date received_date,
        Integer order_quantity
) {
}
