package com.usdtl.ims.departmentMaster.qcQa;

import com.usdtl.ims.departmentMaster.common.MasterEntity;
import com.usdtl.ims.departmentMaster.qcInternalStandardsMaster.QcInternalStandardsEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("qc-qa")
public class QcQaMasterController {
    private QcQaMasterService service;

    @GetMapping("list")
    public Page<QcQaMasterEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return service.getDepartmentMasterItems(page);
    }
    @PostMapping("create")
    public QcQaMasterEntity createDepartmentMasterItem(@RequestBody MasterEntity masterItem) {
        return service.createDepartmentMasterItem(masterItem);
    }
    @GetMapping("grand-total")
    public Double getTotal() {
        return service.getTotal();
    }
}