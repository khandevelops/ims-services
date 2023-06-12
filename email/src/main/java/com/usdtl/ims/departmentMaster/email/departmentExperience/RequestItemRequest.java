package com.usdtl.ims.departmentMaster.email.departmentExperience;

import com.usdtl.ims.departmentMaster.email.constants.Confirmation;
import com.usdtl.ims.departmentMaster.email.constants.Status;

import java.util.Date;

public record RequestItemRequest(
        Integer id,
        Integer item_id,
        Integer quantity,
        String department,
        Status status,
        String location,
        Date time_requested,
        Date time_received,
        Confirmation confirmation,
        String user,
        String comment,
        String custom_text
) {
}
