package com.usdtl.ims.department.receiving;

import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("receiving")
@AllArgsConstructor
@RestController
public class ReceivingController {
    private ReceivingService service;
    @GetMapping("list/transformed")
    public Page<DepartmentMasterResponseTest> getDepartmentTransformedItems(@RequestParam Integer page) {
        return service.getDepartmentTransformedItems(page);
    }

//    @GetMapping("list")
//    public Page<ReceivingEntity> getItemsByPage(@RequestParam Integer page, @RequestParam Integer size) {
//        return service.getItemsByPage(page, size);
//    }
//    @GetMapping(path = "{id}")
//    public ResponseEntity<ReceivingEntity> getItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
//        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
//    }
//    @PostMapping
//    public ResponseEntity<ReceivingEntity> createItem(@RequestBody DepartmentRequest request) {
//        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
//    }
//    @PatchMapping("update-quantity")
//    public List<ReceivingEntity> updateQuantity(@RequestBody List<DepartmentRequest> request) {
//        return service.updateQuantity(request);
//    }
//    @PatchMapping(path = "{id}")
//    public ReceivingEntity updateItemById(@PathVariable(value = "id") Integer id, @RequestBody DepartmentRequest request) {
//        return service.updateItemById(id, request);
//    }
//    @DeleteMapping(path = "{id}")
//    public void deleteItemById(@PathVariable(value = "id")Integer id) {
//        service.deleteItemById(id);
//    }
}
