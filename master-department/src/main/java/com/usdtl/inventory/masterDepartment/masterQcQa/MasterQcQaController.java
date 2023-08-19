package com.usdtl.inventory.masterDepartment.masterQcQa;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("qcqa")
public class MasterQcQaController {
    private MasterQcQaService service;

    @GetMapping(path = "{id}")
    public  ResponseEntity<MasterQcQaEntity> getItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }

    @GetMapping("filter")
    public Page<MasterQcQaEntity> filterItems(@RequestParam String keyword, @RequestParam Integer page) {
        return service.filterItems(keyword, page);
    }

    @GetMapping("sort")
    public Page<MasterQcQaEntity> sortItems(@RequestParam Integer page, @RequestParam String column, @RequestParam String direction) {
        return service.sorItems(page, column, direction);
    }

    @GetMapping("list")
    public Page<MasterQcQaEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }

    @PostMapping("create/{department}")
    public MasterQcQaEntity create(@RequestBody MasterQcQaEntity request, @PathVariable(value = "department") Department department) {
        return create(request, department);
    }

    @PostMapping("assign/{id}/{department}")
    public MasterQcQaEntity assign(@PathVariable(value = "id") Integer id, @PathVariable(value = "department") Department department) {
        return assign(id, department);
    }
}
