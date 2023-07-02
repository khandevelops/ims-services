package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.departmentMaster.common.GrandTotal;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("extractions")
public class ExtractionsMasterController {
    private ExtractionsMasterService extractionsMasterService;

    @GetMapping("list")
    public Page<ExtractionsMasterEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return extractionsMasterService.getDepartmentMasterItems(page);
    }
    @GetMapping("grand-total")
    public Double getTotal() {
        return extractionsMasterService.getTotal();
    }
}