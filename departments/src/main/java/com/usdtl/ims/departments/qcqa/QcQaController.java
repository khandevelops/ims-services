package com.usdtl.ims.departments.qcqa;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("qcqa")
@AllArgsConstructor
@RestController
public class QcQaController {
    private QcQaService service;
    @GetMapping("list")
    public Page<QcQaEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<QcQaEntity> getItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<QcQaEntity> createItem(@RequestBody QcQaEntity request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
    @PatchMapping(path = "{id}")
    public QcQaEntity updateItem(@PathVariable(value = "id") Integer id, @RequestBody QcQaEntity request) {
        return service.updateItem(id, request);
    }
    @DeleteMapping(path = "{id}")
    public void deleteItem(@PathVariable(value = "id")Integer id) {
        service.deleteItem(id);
    }
}
