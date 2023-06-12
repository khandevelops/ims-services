package com.usdtl.ims.departmentMaster.requestDepartment.requestGeneral;

import com.usdtl.ims.departmentMaster.requestDepartment.constants.Confirmation;
import com.usdtl.ims.departmentMaster.requestDepartment.constants.Status;

import java.util.Date;

public record RequestGeneralResponse(
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
