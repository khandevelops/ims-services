package com.usdtl.ims.departmentMaster.extractions;

import com.usdtl.ims.departmentMaster.common.response.DepartmentMasterResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController("ExtractionsMaster")
@RequestMapping("extractions")
public class ExtractionsMasterController {
    private ExtractionsMasterService extractionsMasterService;

    @GetMapping("list")
    public Page<ExtractionsMasterEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return extractionsMasterService.getDepartmentMasterItems(page);
    }
}