package com.usdtl.ims.departments.extractions;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.departments.department.DepartmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("extractions")
@AllArgsConstructor
@RestController
public class ExtractionsController  {
    private ExtractionsService service;
    @GetMapping("list")
    public Page<ExtractionsEntity> getItemsByPage(@RequestParam Integer page) {
        return service.getItemsByPage(page);
    }
    @GetMapping(path = "{id}")
    public  ResponseEntity<ExtractionsEntity> getItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<ExtractionsEntity> createItem(@RequestBody DepartmentRequest request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
    @PatchMapping(path = "/{id}/update")
    public ExtractionsEntity updateItemById(@PathVariable(value = "id") Integer id, @RequestBody DepartmentRequest request) {
        return service.updateItemById(id, request);
    }
    @DeleteMapping(path = "{id}/delete")
    public ResponseEntity<String> deleteItemById(@PathVariable(value = "id")Integer id) {
        return service.deleteItemById(id);
    }
}