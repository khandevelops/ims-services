package com.usdtl.ims.departmentMaster.department.screening;

import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("screening")
@AllArgsConstructor
@RestController
public class ScreeningController {
    private ScreeningService service;
    @GetMapping("list/transformed")
    public Page<DepartmentMasterResponseTest> getDepartmentTransformedItems(@RequestParam Integer page) {
        return service.getDepartmentTransformedItems(page);
    }
    @PatchMapping("update-department-items")
    public List<ScreeningEntity> updateDepartmentItems(@RequestBody List<ScreeningEntity> requestItems) {
        return service.updateDepartmentItems(requestItems);
    }
}
