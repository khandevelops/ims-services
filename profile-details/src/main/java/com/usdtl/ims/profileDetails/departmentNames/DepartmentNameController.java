package com.usdtl.ims.profileDetails.departmentNames;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("department-names")
public class DepartmentNameController {
    private DepartmentNameService service;

    @GetMapping("list")
    public List<DepartmentNameEntity> getDepartmentNames() {
        return service.getDepartmentNames();
    }

    @PostMapping("create")
    public DepartmentNameEntity createDepartmentName(@RequestBody DepartmentNameEntity departmentName) {
        return service.createDepartmentName(departmentName);
    }
}
