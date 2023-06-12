package com.usdtl.ims.departmentMaster.requestDepartment.requestOfficeSupply;

import com.usdtl.ims.departmentMaster.requestDepartment.constants.Confirmation;
import com.usdtl.ims.departmentMaster.requestDepartment.constants.Status;

import java.util.Date;

public record RequestOfficeSupplyResponse(
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
