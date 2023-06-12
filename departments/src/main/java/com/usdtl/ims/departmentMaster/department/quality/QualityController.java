package com.usdtl.ims.departmentMaster.department.quality;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.departmentMaster.department.department.DepartmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("quality")
@AllArgsConstructor
@RestController
public class QualityController {
    private QualityService service;
    @GetMapping("list")
    public Page<QualityEntity> getItemsByPage(@RequestParam Integer page) {
        return service.getItemsByPage(page);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<QualityEntity> getItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<QualityEntity> createItem(@RequestBody DepartmentRequest request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
    @PatchMapping("update-quantity")
    public List<QualityEntity> updateQuantity(@RequestBody List<DepartmentRequest> request) {
        return service.updateQuantity(request);
    }
    @PatchMapping(path = "{id}")
    public QualityEntity updateItemById(@PathVariable(value = "id") Integer id, @RequestBody DepartmentRequest request) {
        return service.updateItemById(id, request);
    }
    @DeleteMapping(path = "{id}")
    public void deleteItemById(@PathVariable(value = "id")Integer id) {
        service.deleteItemById(id);
    }
}
