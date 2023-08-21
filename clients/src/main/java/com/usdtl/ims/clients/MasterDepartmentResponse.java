package com.usdtl.ims.clients;

import com.usdtl.ims.clients.responseRecord.DepartmentResponse;

import java.util.List;
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
        Double unit_price,
        String category,
        String comment,
        String type,
        String group,
        List<DepartmentResponse> departmentItems
) {
}
