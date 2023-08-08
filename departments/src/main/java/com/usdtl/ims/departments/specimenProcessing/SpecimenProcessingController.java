package com.usdtl.ims.departments.specimenProcessing;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.departments.department.DepartmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("specimen_processing")
@AllArgsConstructor
@RestController
public class SpecimenProcessingController {
    private SpecimenProcessingService service;
    @GetMapping("list")
    public Page<SpecimenProcessingEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }
    @GetMapping(path = "{id}")
    public  ResponseEntity<SpecimenProcessingEntity> getItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<SpecimenProcessingEntity> createItem(@RequestBody SpecimenProcessingEntity request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
    @PatchMapping(path = "/{id}/update")
    public SpecimenProcessingEntity updateItem(@PathVariable(value = "id") Integer id, @RequestBody SpecimenProcessingEntity request) {
        return service.updateItem(id, request);
    }
    @DeleteMapping(path = "{id}")
    public void deleteItem(@PathVariable(value = "id")Integer id) {
        service.deleteItem(id);
    }
}