package com.usdtl.ims.downloads.massSpec;

import com.usdtl.ims.clients.responseClass.DepartmentDownloadResponse;
import com.usdtl.ims.downloads.excelExporter.DownloadDepartmentItems;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("mass-spec")
public class MassSpecController {
    private MassSpecService service;

    @GetMapping("list")
    public void getDepartmentTransformedItems(HttpServletResponse response) throws IOException {
        log.info("List extractions master transformed items");
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<DepartmentDownloadResponse> departmentDownloadResponseItems = service.getDepartmentTransformedItems();

        DownloadDepartmentItems downloadDepartmentItems = new DownloadDepartmentItems(departmentDownloadResponseItems);

        downloadDepartmentItems.export(response);
    }
}