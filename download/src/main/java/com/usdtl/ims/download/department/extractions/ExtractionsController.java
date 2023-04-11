package com.usdtl.ims.download.department.extractions;

import com.usdtl.ims.clients.responseClass.DepartmentDownloadResponse;
import com.usdtl.ims.download.department.excelExporter.DownloadDepartmentItems;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("extractions")
public class ExtractionsController {
    private ExtractionsService service;

    @GetMapping("list/download")
    public void getDepartmentTransformedItems(HttpServletResponse response) throws IOException {
        log.info("List extractions master transformed items");
        List<DepartmentDownloadResponse> departmentDownloadResponseItems = service.getDepartmentTransformedItems();

        DownloadDepartmentItems downloadDepartmentItems = new DownloadDepartmentItems(departmentDownloadResponseItems);

        downloadDepartmentItems.export(response);
    }

    @GetMapping("list/test")
    public List<DepartmentDownloadResponse> test() {
        log.info("List extractions master transformed items");
        return service.getDepartmentTransformedItems();
    }
}