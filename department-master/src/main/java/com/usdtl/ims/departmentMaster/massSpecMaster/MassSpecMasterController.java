package com.usdtl.ims.departmentMaster.massSpecMaster;

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
@RequestMapping("mass-spec")
public class MassSpecMasterController {
    private MassSpecMasterService massSpecMasterService;

    @GetMapping("list")
    public Page<MassSpecMasterEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return massSpecMasterService.getDepartmentMasterItems(page);
    }
}