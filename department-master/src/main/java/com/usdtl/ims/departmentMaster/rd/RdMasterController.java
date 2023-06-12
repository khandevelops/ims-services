package com.usdtl.ims.departmentMaster.rd;

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
@RestController("RdMaster")
@RequestMapping("rd")
public class RdMasterController {
    private RdMasterService rdMasterService;

    @GetMapping("list")
    public Page<RdMasterEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return rdMasterService.getDepartmentMasterItems(page);
    }
}