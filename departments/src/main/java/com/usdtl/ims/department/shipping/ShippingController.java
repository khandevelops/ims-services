package com.usdtl.ims.department.shipping;

import com.usdtl.ims.common.exceptions.NotFoundException;
import com.usdtl.ims.department.department.DepartmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("shipping")
@AllArgsConstructor
@RestController
public class ShippingController {
    private ShippingService service;
    @GetMapping("list")
    public Page<ShippingEntity> getItemsByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return service.getItemsByPage(page, size);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<ShippingEntity> getItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ShippingEntity> createItem(@RequestBody DepartmentRequest request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
    @PatchMapping("update-quantity")
    public List<ShippingEntity> updateQuantity(@RequestBody List<DepartmentRequest> request) {
        return service.updateQuantity(request);
    }
    @PatchMapping(path = "{id}")
    public ShippingEntity updateItemById(@PathVariable(value = "id") Integer id, @RequestBody DepartmentRequest request) {
        return service.updateItemById(id, request);
    }
    @DeleteMapping(path = "{id}")
    public void deleteItemById(@PathVariable(value = "id")Integer id) {
        service.deleteItemById(id);
    }
}
