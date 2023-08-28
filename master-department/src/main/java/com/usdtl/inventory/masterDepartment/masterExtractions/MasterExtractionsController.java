package com.usdtl.inventory.masterDepartment.masterExtractions;

import com.usdtl.ims.clients.response.MasterDepartmentResponse;
import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("extractions")
public class MasterExtractionsController {
    private MasterExtractionsService service;

    @GetMapping(path = "{id}")
    public  ResponseEntity<MasterExtractionsEntity> getItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }

    @GetMapping("filter")
    public Page<MasterExtractionsEntity> filterItems(@RequestParam String keyword, @RequestParam Integer page) {
        return service.filterItems(keyword, page);
    }

    @GetMapping("sort")
    public Page<MasterExtractionsEntity> sortItems(@RequestParam Integer page, @RequestParam String column, @RequestParam String direction) {
        return service.sortItems(page, column, direction);
    }

    @GetMapping("list")
    public Page<MasterExtractionsEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }

    @PostMapping("create/{department}")
    public MasterExtractionsEntity create(@RequestBody MasterExtractionsEntity request, @PathVariable(value = "department") Department department) {
        return service.create(request, department);
    }

    @PostMapping("assign/{id}/{department}")
    public MasterExtractionsEntity assign(@PathVariable(value = "id") Integer id, @PathVariable(value = "department") Department department) {
        return service.assign(id, department);
    }
}
