package com.usdtl.ims.departmentMaster.qcQa;

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
@RequestMapping("qc-qa")
public class QcQaMasterController {
    private QcQaMasterService qcQaMasterService;

    @GetMapping("list")
    public Page<QcQaMasterEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return qcQaMasterService.getDepartmentMasterItems(page);
    }

    @GetMapping("grand-total")
    public Double getTotal() {
        return qcQaMasterService.getTotal();
    }
}