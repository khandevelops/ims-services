package com.usdtl.ims.departmentMaster.qcInternalStandards;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
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
@RequestMapping("qc-internal-standards")
public class QcInternalStandardsController {
    private QcInternalStandardsService qcInternalStandardsService;

    @GetMapping("list")
    public Page<QcInternalStandardsEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return qcInternalStandardsService.getDepartmentMasterItems(page);
    }

    @GetMapping("grand-total")
    public Double getTotal() {
        return qcInternalStandardsService.getTotal();
    }
}