package com.usdtl.ims.departmentMaster.rd;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import com.usdtl.ims.departmentMaster.common.MasterEntity;
import com.usdtl.ims.departmentMaster.qcQa.QcQaMasterEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController("RdMaster")
@RequestMapping("rd")
public class RdMasterController {
    private RdMasterService service;

    @GetMapping("list")
    public Page<RdMasterEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return service.getDepartmentMasterItems(page);
    }
    @PostMapping("create")
    public RdMasterEntity createDepartmentMasterItem(@RequestBody MasterEntity masterItem) {
        return service.createDepartmentMasterItem(masterItem);
    }
    @GetMapping("grand-total")
    public Double getTotal() {
        return service.getTotal();
    }
}