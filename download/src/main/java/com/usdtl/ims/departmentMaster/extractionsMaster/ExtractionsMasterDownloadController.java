package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.clients.responseRecord.DepartmentMasterDownloadExcelResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("extractions")
public class ExtractionsMasterDownloadController {
    private ExtractionsMasterDownloadService service;

    @GetMapping("list/download")
    public List<DepartmentMasterDownloadExcelResponse> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return service.getDepartmentMasterExcelItems();
    }
}