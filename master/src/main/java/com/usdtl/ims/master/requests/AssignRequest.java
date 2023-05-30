package com.usdtl.ims.master.requests;

import com.usdtl.ims.common.constants.Department;

public record AssignRequest (
        Department department,
        Integer masterItemId
) {

}
