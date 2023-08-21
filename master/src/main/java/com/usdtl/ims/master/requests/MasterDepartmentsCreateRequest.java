package com.usdtl.ims.master.requests;

import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.ims.master.MasterEntity;

import java.util.List;

public record MasterDepartmentsCreateRequest(
        MasterEntity masterItem,
        List<Department> departments
) {

}
