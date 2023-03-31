package com.usdtl.ims.departmentMaster.shippingMaster;

import com.usdtl.ims.common.exceptions.NotFoundException;
import com.usdtl.ims.departmentMaster.response.DepartmentTransformedResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("shipping")
public class ShippingMasterController {
    private ShippingMasterService service;

    @GetMapping("list/transformed")
    public Page<DepartmentTransformedResponse> getItemsByPage(@RequestParam Integer page) {
        return service.getExperienceItemsByPage(page);
    }
    @GetMapping("list")
    public Page<ShippingMasterEntity> getExtractionsExperienceItemsByPage(@RequestParam Integer page) {
        return service.getExtractionsExperienceItemsByPage(page);
    }
    @GetMapping(path = "{id}")
    public  ResponseEntity<ShippingMasterEntity> getItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShippingMasterEntity> createItem(@RequestBody ShippingMasterRequest request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
}