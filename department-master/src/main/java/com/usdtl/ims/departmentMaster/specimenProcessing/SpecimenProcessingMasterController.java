package com.usdtl.ims.departmentMaster.specimenProcessing;

import com.usdtl.ims.clients.DepartmentMasterResponse;
import com.usdtl.ims.common.exceptions.common.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("receiving")
public class SpecimenProcessingMasterController {
    private SpecimenProcessingMasterService service;

    @GetMapping("list")
    public Page<DepartmentMasterResponse> getDepartmentMasterItems(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return service.getDepartmentMasterItems(page);
    }

    @GetMapping("list/transformed")
    public Page<DepartmentMasterResponse> getItemsByPage(@RequestParam Integer page) {
        return service.getExperienceItemsByPage(page);
    }
    @GetMapping("list/raw")
    public Page<SpecimenProcessingMasterEntity> getExtractionsExperienceItemsByPage(@RequestParam Integer page) {
        return service.getExtractionsExperienceItemsByPage(page);
    }
    @GetMapping(path = "{id}")
    public  ResponseEntity<SpecimenProcessingMasterEntity> getItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SpecimenProcessingMasterEntity> createItem(@RequestBody SpecimenProcessingMasterRequest request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
}