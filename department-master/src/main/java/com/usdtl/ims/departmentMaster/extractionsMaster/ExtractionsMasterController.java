package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.departmentMaster.common.GrandTotal;
import com.usdtl.ims.departmentMaster.common.MasterEntity;
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
    private ExtractionsMasterService service;
    @PostMapping("assign")
    public ExtractionsMasterEntity assignItem(@RequestBody MasterEntity masterItem) {
        return service.assignItem(masterItem);
    }
    @GetMapping("grand-total")
    public Double getTotal() {
        return service.getTotal();
    }
}