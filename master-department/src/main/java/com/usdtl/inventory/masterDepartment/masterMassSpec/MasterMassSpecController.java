package com.usdtl.inventory.masterDepartment.masterMassSpec;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsEntity;
import com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("mass-spec")
public class MasterMassSpecController {
    private MasterMassSpecService service;

    @GetMapping(path = "{id}")
    public  ResponseEntity<MasterMassSpecEntity> getItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }

    @GetMapping("filter")
    public Page<MasterMassSpecEntity> filterItems(@RequestParam String keyword, @RequestParam Integer page) {
        return service.filterItems(keyword, page);
    }

    @GetMapping("sort")
    public Page<MasterMassSpecEntity> sortItems(@RequestParam Integer page, @RequestParam String column, @RequestParam String direction) {
        return service.sortItems(page, column, direction);
    }

    @GetMapping("list")
    public Page<MasterMassSpecEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }

    @PostMapping("create/{department}")
    public MasterMassSpecEntity create(@RequestBody MasterMassSpecEntity request, @PathVariable(value = "department") Department department) {
        return create(request, department);
    }

    @PatchMapping("{id}/assign")
    public MasterMassSpecEntity assign(@PathVariable(value = "id") Integer id, @PathVariable(value = "department") Department department) {
        return assign(id, department);
    }

    @GetMapping("sync-order-details")
    public String syncOrderDetails() {
        return service.syncOrderDetails();
    }
}
