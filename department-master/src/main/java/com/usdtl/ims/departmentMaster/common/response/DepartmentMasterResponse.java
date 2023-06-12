package com.usdtl.ims.departmentMaster.common.response;

import com.usdtl.ims.clients.response.MasterResponse;

import java.util.Date;

public record DepartmentMasterResponse(
        Integer id,
        Integer item_id,
        String location,
        Integer quantity,
        Integer min_quantity,
        Integer max_quantity,
        String lot_number,
        Date expiration_date,
        Date received_date,
        MasterResponse masterItem
) {
}
