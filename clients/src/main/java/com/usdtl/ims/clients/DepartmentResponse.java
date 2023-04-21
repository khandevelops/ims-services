package com.usdtl.ims.clients;

import java.util.Date;

public record DepartmentResponse (
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
) { }
