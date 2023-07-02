package com.usdtl.ims.clients.response;

import com.usdtl.ims.common.exceptions.constants.Category;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record MasterDepartmentResponse(
        Integer id,
        String item,
        String manufacturer,
        String part_number,
        String recent_cn,
        String recent_vendor,
        String fisher_cn,
        String vwr_cn,
        String lab_source_cn,
        String other_cn,
        String purchase_unit,
        Double average_unit_price,
        String category,
        String comment,
        String type,
        String group,
        String drug_class,
        String usage_level,
        Date expiration_date,
        Date received_date,
        Double total_price,
        Integer total_quantity,
        Integer order_quantity,
        Integer minimum_quantity,
        Integer maximum_quantity,
        List<DepartmentResponse> departmentItems


) {
}
