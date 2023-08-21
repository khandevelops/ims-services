package com.usdtl.ims.clients.responseRecord;

import com.usdtl.ims.common.exceptions.constants.Confirmation;
import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.ims.common.exceptions.constants.Status;
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
        String detail,
        String custom_text,
        MasterItemRequest masterItem
) {
}
