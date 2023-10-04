package com.usdtl.inventory.masterDepartment.masterProcessingLab;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("processing-lab")
public class MasterProcessingLabController {
    private MasterProcessingLabService service;

    @GetMapping(path = "{id}")
    public  ResponseEntity<MasterProcessingLabEntity> getItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }

    @GetMapping("filter")
    public Page<MasterProcessingLabEntity> filterItems(@RequestParam String keyword, @RequestParam Integer page) {
        return service.filterItems(keyword, page);
    }

    @GetMapping("sort")
    public Page<MasterProcessingLabEntity> sortItems(@RequestParam Integer page, @RequestParam String column, @RequestParam String direction) {
        return service.sorItems(page, column, direction);
    }

    @GetMapping("list")
    public Page<MasterProcessingLabEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }

    @PostMapping("create/{department}")
    public MasterProcessingLabEntity create(@RequestBody MasterProcessingLabEntity request, @PathVariable(value = "department") Department department) {
        return create(request, department);
    }

    @PatchMapping("{id}/assign")
    public MasterProcessingLabEntity assign(@PathVariable(value = "id") Integer id, @PathVariable(value = "department") Department department) {
        return assign(id, department);
    }

    @GetMapping("sync-order-details")
    public String syncOrderDetails() {
        return service.syncOrderDetails();
    }
}
