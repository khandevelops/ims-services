package com.usdtl.inventory.masterDepartment.masterMassSpec;

import com.usdtl.ims.clients.response.MasterDepartmentResponse;
import com.usdtl.ims.common.exceptions.common.NotFoundException;
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

    @GetMapping("list/transformed")
    public Page<MasterDepartmentResponse> getMasterDepartmentItemsTransformed(@RequestParam Integer page) {
        return service.getMasterDepartmentItemsTransformed(page);
    }

    @GetMapping("list")
    public Page<MasterMassSpecEntity> getMasterDepartmentItems(@RequestParam Integer page) {
        return service.getMasterDepartmentItems(page);
    }
}
