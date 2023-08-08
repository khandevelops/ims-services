package com.usdtl.inventory.masterDepartment.masterRd;

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
@RequestMapping("rd")
public class MasterRdController {
    private MasterExtractionsService service;

    @GetMapping(path = "{id}")
    public  ResponseEntity<com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsEntity> getMasterExtractionsItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }

    @GetMapping("list")
    public Page<com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsEntity> getMasterDepartmentItems(@RequestParam Integer page) {
        return service.getMasterDepartmentItems(page);
    }

    @PostMapping("create/{department}")
    public com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsEntity create(@RequestBody com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsEntity request, @PathVariable(value = "department") Department department) {
        return create(request, department);
    }

    @PostMapping("assign/{id}/{department}")
    public MasterExtractionsEntity assign(@PathVariable(value = "id") Integer id, @PathVariable(value = "department") Department department) {
        return assign(id, department);
    }
}
