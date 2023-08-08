package com.usdtl.inventory.masterDepartment.masterMassSpec;

import com.usdtl.ims.clients.response.MasterDepartmentResponse;
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
@RequestMapping("mass-spec")
public class MasterMassSpecController {
    private MasterMassSpecService service;

    @GetMapping(path = "{id}")
    public  ResponseEntity<MasterMassSpecEntity> getMasterMassSpecItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }

    @GetMapping("list")
    public Page<MasterMassSpecEntity> getMasterDepartmentItems(@RequestParam Integer page) {
        return service.getMasterDepartmentItems(page);
    }

    @PostMapping("create/{department}")
    public MasterMassSpecEntity create(@RequestBody MasterMassSpecEntity request, @PathVariable(value = "department") Department department) {
        return create(request, department);
    }

    @PostMapping("assign/{id}/{department}")
    public MasterMassSpecEntity assign(@PathVariable(value = "id") Integer id, @PathVariable(value = "department") Department department) {
        return assign(id, department);
    }
}
