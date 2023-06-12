package com.usdtl.ims.departmentMaster.department.massSpec;

import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("mass-spec")
@AllArgsConstructor
@RestController
public class MassSpecController  {
    private MassSpecService service;
    @GetMapping("list/transformed")
    public Page<DepartmentMasterResponseTest> getDepartmentTransformedItems(@RequestParam Integer page) {
        return service.getDepartmentTransformedItems(page);
    }
    @PatchMapping("update-department-items")
    public List<MassSpecEntity> updateDepartmentItems(@RequestBody List<MassSpecEntity> requestItems) {
        return service.updateDepartmentItems(requestItems);
    }
}
