package com.usdtl.ims.requestMaster.request;

import com.usdtl.ims.common.exceptions.constants.Confirmation;
import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.ims.common.exceptions.constants.Status;
import lombok.Builder;

@Builder
public record RequestMasterTransformedDepartmentRequest(
        Integer quantity,
        Department department,
        String user,
        Status status,
        String detail,
        String custom_text,
        Confirmation confirmation,
        String location,
        Integer master_item_id,
        Integer request_item_id
) {
}
