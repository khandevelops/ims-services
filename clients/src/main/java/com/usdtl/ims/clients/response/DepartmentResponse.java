package com.usdtl.ims.clients.response;

import lombok.Builder;

import java.util.Date;
@Builder
public record DepartmentResponse(
        Integer id,
        Integer item_id,
        String location,
        Integer quantity,
        Integer min_quantity,
        Integer max_quantity,
        String lot_number,
        Date expiration_date,
        Date received_date
) { }
