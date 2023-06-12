package com.usdtl.ims.master.requests;

import com.usdtl.ims.common.exceptions.constants.Department;

public record AssignRequest (
        Department department,
        Integer masterItemId
) {

}
