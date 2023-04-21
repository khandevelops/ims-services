package com.usdtl.ims.department.receiving;

import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import com.usdtl.ims.common.exceptions.NotFoundException;
import com.usdtl.ims.department.extractions.ExtractionsEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("receiving")
@AllArgsConstructor
@RestController
public class ReceivingController {
    private ReceivingService service;
    @GetMapping("list/transformed")
    public Page<DepartmentMasterResponseTest> getDepartmentTransformedItems(@RequestParam Integer page) {
        return service.getDepartmentTransformedItems(page);
    }
    @PatchMapping("update-department-items")
    public List<ReceivingEntity> updateDepartmentItems(@RequestBody List<ReceivingEntity> requestItems) {
        return service.updateDepartmentItems(requestItems);
    }
}
