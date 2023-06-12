package com.usdtl.ims.departmentMaster.department.extractions;

import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("extractions")
@AllArgsConstructor
@RestController
public class ExtractionsController  {
    private ExtractionsService service;

    @GetMapping("list")
    public Page<ExtractionsEntity> getDepartmentItems(@RequestParam Integer page) {
        return service.getDepartmentItems(page);
    }

    @GetMapping("list/transformed")
    public Page<DepartmentMasterResponseTest> getDepartmentTransformedItems(@RequestParam Integer page) {
        return service.getDepartmentTransformedItems(page);
    }

    @PatchMapping("update-department-items")
    public List<ExtractionsEntity> updateDepartmentItems(@RequestBody List<ExtractionsEntity> requestItems) {
        return service.updateDepartmentItems(requestItems);
    }
}