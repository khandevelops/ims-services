package com.usdtl.ims.departmentMaster.processingLabMaster;

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
@RequestMapping("processing-lab")
public class ProcessingLabMasterController {
    private ProcessingLabMasterService processingLabMasterService;

    @GetMapping("list")
    public Page<ProcessingLabMasterEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return processingLabMasterService.getDepartmentMasterItems(page);
    }

    @GetMapping("grand-total")
    public Double getTotal() {
        return processingLabMasterService.getTotal();
    }
}