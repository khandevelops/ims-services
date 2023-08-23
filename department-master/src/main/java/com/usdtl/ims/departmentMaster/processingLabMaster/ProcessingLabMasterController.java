package com.usdtl.ims.departmentMaster.processingLabMaster;

import com.usdtl.ims.departmentMaster.common.MasterEntity;
import com.usdtl.ims.departmentMaster.massSpecMaster.MassSpecMasterEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("processing-lab")
public class ProcessingLabMasterController {
    private ProcessingLabMasterService service;

    @GetMapping("list")
    public Page<ProcessingLabMasterEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return service.getDepartmentMasterItems(page);
    }
    @PostMapping("create")
    public ProcessingLabMasterEntity createDepartmentMasterItem(@RequestBody MasterEntity masterItem) {
        return service.createDepartmentMasterItem(masterItem);
    }
    @GetMapping("grand-total")
    public Double getTotal() {
        return service.getTotal();
    }
}