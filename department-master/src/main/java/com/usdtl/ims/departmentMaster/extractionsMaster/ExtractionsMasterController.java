package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("extractions")
public class ExtractionsMasterController {
    private ExtractionsMasterService service;

    @GetMapping("list/transformed")
    public Page<DepartmentMasterResponse> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return service.getDepartmentMasterItems(page);
    }
}