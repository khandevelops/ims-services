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
    public Page<ExtractionsEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }
    @GetMapping(path = "{id}")
    public  ResponseEntity<ExtractionsEntity> getItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<ExtractionsEntity> createItem(@RequestBody ExtractionsEntity request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
    @PatchMapping(path = "/{id}/update")
    public ExtractionsEntity updateItem(@PathVariable(value = "id") Integer id, @RequestBody ExtractionsEntity request) {
        return service.updateItem(id, request);
    }
    @DeleteMapping(path = "{id}/delete")
    public ResponseEntity<String> deleteItem(@PathVariable(value = "id")Integer id) {
        return service.deleteItem(id);
    }
}