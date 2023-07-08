package com.usdtl.ims.master.responses;

import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.ims.master.MasterEntity;

public record AssignResponse(
        Department department,
        MasterEntity masterItem
) {
}
