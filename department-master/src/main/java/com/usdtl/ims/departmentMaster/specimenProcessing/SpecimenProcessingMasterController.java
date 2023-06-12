package com.usdtl.ims.departmentMaster.specimenProcessing;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController("SpecimenProcessingMaster")
@RequestMapping("specimen-processing")
public class SpecimenProcessingMasterController {
    private SpecimenProcessingMasterService specimenProcessingMasterService;

    @GetMapping("list")
    public Page<SpecimenProcessingMasterEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return specimenProcessingMasterService.getDepartmentMasterItems(page);
    }
}