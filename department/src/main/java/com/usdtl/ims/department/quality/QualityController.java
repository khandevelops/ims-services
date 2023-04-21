package com.usdtl.ims.department.quality;

import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import com.usdtl.ims.common.exceptions.NotFoundException;
import com.usdtl.ims.department.extractions.ExtractionsEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("quality")
@AllArgsConstructor
@RestController
public class QualityController {
    private QualityService service;

    @GetMapping("list/transformed")
    public Page<DepartmentMasterResponseTest> getDepartmentTransformedItems(@RequestParam Integer page) {
        return service.getDepartmentTransformedItems(page);
    }

    @PatchMapping("update-department-items")
    public List<QualityEntity> updateDepartmentItems(@RequestBody List<QualityEntity> requestItems) {
        return service.updateDepartmentItems(requestItems);
    }
}
