package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.clients.DepartmentMasterResponse;
import com.usdtl.ims.clients.request.ExtractionsMasterRequest;
import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("extractions")
public class ExtractionsMasterController {
    private ExtractionsMasterService service;

    @GetMapping("list")
    public Page<DepartmentMasterResponseTest> getDepartmentMasterItems(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return service.getDepartmentMasterItems(page);
    }

    @GetMapping("list/transformed")
    public Page<DepartmentMasterResponse> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return service.getExperienceItemsByPage(page);
    }
    @GetMapping("list/raw")
    public Page<ExtractionsMasterEntity> getExtractionsExperienceItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master items");
        return service.getExtractionsExperienceItemsByPage(page);
    }
    @GetMapping("scheduled/email/list")
    public List<DepartmentMasterResponse> getScheduledEmailItems() {
        log.info("List scheduled email items");
        return service.getScheduledEmailItems();
    }
    @GetMapping(path = "{id}")
    public  ResponseEntity<ExtractionsMasterEntity> getItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        log.info("Get extractions master item by id: {}", id);
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExtractionsMasterEntity> createItem(@RequestBody ExtractionsMasterRequest request) {
        log.info("Create extractions master item");
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
}