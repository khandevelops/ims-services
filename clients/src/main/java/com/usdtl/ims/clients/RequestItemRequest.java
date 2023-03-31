package com.usdtl.ims.clients;

import com.usdtl.ims.common.constants.Confirmation;
import com.usdtl.ims.common.constants.Department;
import com.usdtl.ims.common.constants.Status;
import lombok.Builder;

import java.util.Date;

@Builder
public record RequestItemRequest(
        Integer id,
        Integer item_id,
        Integer order_quantity,
        Department department,
        Status status,
        String location,
        Date time_requested,
        Date time_updated,
        Confirmation confirmation,
        String user,
        String comment,
        String custom_text,
        MasterItemRequest masterItem
) {
}
