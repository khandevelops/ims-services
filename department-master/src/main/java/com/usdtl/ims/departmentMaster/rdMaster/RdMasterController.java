package com.usdtl.ims.departmentMaster.rdMaster;

import com.usdtl.ims.common.exceptions.NotFoundException;
import com.usdtl.ims.departmentMaster.response.DepartmentTransformedResponse;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("rd")
public class RdMasterController {
    private RdMasterService service;
    @GetMapping("list/transformed")
    public Page<DepartmentTransformedResponse> getItemsByPage(@RequestParam Integer page) {
        return service.getExperienceItemsByPage(page);
    }
    @GetMapping("list")
    public Page<RdMasterEntity> getExtractionsExperienceItemsByPage(@RequestParam Integer page) {
        return service.getExtractionsExperienceItemsByPage(page);
    }
    @GetMapping(path = "{id}")
    public  ResponseEntity<RdMasterEntity> getItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RdMasterEntity> createItem(@RequestBody RdMasterRequest request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
}