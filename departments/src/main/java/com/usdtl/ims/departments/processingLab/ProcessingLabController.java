package com.usdtl.ims.departments.processingLab;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("processing_lab")
@AllArgsConstructor
@RestController
public class ProcessingLabController {
    private ProcessingLabService service;
    @GetMapping("list")
    public Page<ProcessingLabEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }
    @GetMapping(path = "{id}")
    public  ResponseEntity<ProcessingLabEntity> getItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProcessingLabEntity> createItem(@RequestBody ProcessingLabEntity request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
    @PatchMapping(path = "/{id}/update")
    public ProcessingLabEntity updateItem(@PathVariable(value = "id") Integer id, @RequestBody ProcessingLabEntity request) {
        return service.updateItem(id, request);
    }
    @DeleteMapping(path = "{id}")
    public void deleteItem(@PathVariable(value = "id")Integer id) {
        service.deleteItem(id);
    }
}