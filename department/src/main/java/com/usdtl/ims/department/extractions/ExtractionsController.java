package com.usdtl.ims.department.extractions;

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

    @GetMapping("list/transformed")
    public Page<DepartmentMasterResponseTest> getDepartmentTransformedItems(@RequestParam Integer page) {
        return service.getDepartmentTransformedItems(page);
    }

//    @GetMapping("list/master/transformed")
//    public Page<DepartmentMasterResponseTest> getDepartmentMasterTransformedItems(@RequestParam Integer page) {
//        return service.getDepartmentMasterTransformedItems(page);
//    }
}