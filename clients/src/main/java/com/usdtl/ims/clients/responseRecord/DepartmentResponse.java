package com.usdtl.ims.clients.responseRecord;

import java.util.Date;

public record DepartmentResponse (
        Integer id,
        Integer item_id,
        String location,
        Integer quantity,
        String lot_number,
        Date expiration_date,
        Date received_date,
        String usage_level,
        Integer min_quantity,
        Integer max_quantity
) { }
