package com.usdtl.ims.departments.qcInternalStandards;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.departments.department.DepartmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("qc_internal_standards")
@AllArgsConstructor
@RestController
public class QcInternalStandardsController {
    private QcInternalStandardsService service;
    @GetMapping("list")
    public Page<QcInternalStandardsEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }
    @GetMapping(path = "{id}")
    public  ResponseEntity<QcInternalStandardsEntity> getItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<QcInternalStandardsEntity> createItem(@RequestBody QcInternalStandardsEntity request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
    @PatchMapping(path = "/{id}/update")
    public QcInternalStandardsEntity updateItem(@PathVariable(value = "id") Integer id, @RequestBody QcInternalStandardsEntity request) {
        return service.updateItem(id, request);
    }
    @DeleteMapping(path = "{id}")
    public void deleteItem(@PathVariable(value = "id")Integer id) {
        service.deleteItem(id);
    }
}