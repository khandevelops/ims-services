package com.usdtl.inventory.masterDepartment.masterExtractions;

import com.usdtl.ims.clients.responseRecord.MasterDepartmentResponse;
import com.usdtl.ims.common.exceptions.NotFoundException;
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
    public  ResponseEntity<MasterExtractionsEntity> getMasterExtractionsItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }

    @GetMapping("list")
    public Page<MasterExtractionsEntity> getMasterDepartmentItems(@RequestParam Integer page) {
        return service.getMasterDepartmentItems(page);
    }

    @GetMapping("list/transformed")
    public Page<MasterDepartmentResponse> getMasterDepartmentPageableItems(@RequestParam Integer page) {
        return service.getMasterDepartmentPageableItems(page);
    }
}
