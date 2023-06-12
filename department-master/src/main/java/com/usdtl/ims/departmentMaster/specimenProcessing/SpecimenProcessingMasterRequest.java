package com.usdtl.ims.departmentMaster.specimenProcessing;

import lombok.Builder;

import java.util.Date;

@Builder
public record SpecimenProcessingMasterRequest(
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
