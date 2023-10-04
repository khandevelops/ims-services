package com.usdtl.inventory.masterDepartment.masterScreening;

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
@RequestMapping("screening")
public class MasterScreeningController {
    private MasterScreeningService service;

    @GetMapping(path = "{id}")
    public  ResponseEntity<MasterScreeningEntity> getItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }

    @GetMapping("filter")
    public Page<MasterScreeningEntity> filterItems(@RequestParam String keyword, @RequestParam Integer page) {
        return service.filterItems(keyword, page);
    }

    @GetMapping("sort")
    public Page<MasterScreeningEntity> sortItems(@RequestParam Integer page, @RequestParam String column, @RequestParam String direction) {
        return service.sorItems(page, column, direction);
    }

    @GetMapping("list")
    public Page<MasterScreeningEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }

    @PostMapping("{department}/create")
    public MasterScreeningEntity create(@RequestBody MasterScreeningEntity request, @PathVariable(value = "department") Department department) {
        return service.create(request, department);
    }

    @PatchMapping("{id}/assign")
    public MasterScreeningEntity assign(@PathVariable(value = "id") Integer id, @PathVariable(value = "department") Department department) {
        return service.assign(id, department);
    }

    @GetMapping("sync-order-details")
    public String syncOrderDetails() {
        return service.syncOrderDetails();
    }
}
