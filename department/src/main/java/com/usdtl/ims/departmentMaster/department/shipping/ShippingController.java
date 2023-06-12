package com.usdtl.ims.departmentMaster.department.shipping;

import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("shipping")
@AllArgsConstructor
@RestController
public class ShippingController {
    private ShippingService service;
    @GetMapping("list/transformed")
    public Page<DepartmentMasterResponseTest> getDepartmentTransformedItems(@RequestParam Integer page) {
        return service.getDepartmentTransformedItems(page);
    }
    @PatchMapping("update-department-items")
    public List<ShippingEntity> updateDepartmentItems(@RequestBody List<ShippingEntity> requestItems) {
        return service.updateDepartmentItems(requestItems);
    }
}
