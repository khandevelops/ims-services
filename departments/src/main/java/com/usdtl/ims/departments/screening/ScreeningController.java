package com.usdtl.ims.departments.screening;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.departments.department.DepartmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("screening")
@AllArgsConstructor
@RestController
public class ScreeningController {
    private ScreeningService service;
    @GetMapping("list")
    public Page<ScreeningEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<ScreeningEntity> getItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ScreeningEntity> createItem(@RequestBody ScreeningEntity request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
    @PatchMapping(path = "{id}")
    public ScreeningEntity updateItem(@PathVariable(value = "id") Integer id, @RequestBody ScreeningEntity request) {
        return service.updateItem(id, request);
    }
    @DeleteMapping(path = "{id}")
    public void deleteItem(@PathVariable(value = "id")Integer id) {
        service.deleteItem(id);
    }
}
