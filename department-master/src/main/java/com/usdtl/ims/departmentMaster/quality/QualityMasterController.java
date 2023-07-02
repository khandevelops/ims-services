package com.usdtl.ims.departmentMaster.quality;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("quality")
public class QualityMasterController {
    private QualityMasterService qualityMasterService;

    @GetMapping("list")
    public Page<QualityMasterEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return qualityMasterService.getDepartmentMasterItems(page);
    }

    @GetMapping("grand-total")
    public Double getTotal() {
        return qualityMasterService.getTotal();
    }
}