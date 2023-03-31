package com.usdtl.ims.departmentMaster.massSpecMaster;

import com.usdtl.ims.common.exceptions.NotFoundException;
import com.usdtl.ims.departmentMaster.response.DepartmentTransformedResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("mass-spec")
public class MassSpecMasterController {
    private MassSpecMasterService service;

    @GetMapping("list/transformed")
    public Page<DepartmentTransformedResponse> getItemsByPage(@RequestParam Integer page) {
        return service.getExperienceItemsByPage(page);
    }
    @GetMapping("list")
    public Page<MassSpecMasterEntity> getExtractionsExperienceItemsByPage(@RequestParam Integer page) {
        return service.getExtractionsExperienceItemsByPage(page);
    }
    @GetMapping(path = "{id}")
    public  ResponseEntity<MassSpecMasterEntity> getItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MassSpecMasterEntity> createItem(@RequestBody MassSpecMasterRequest request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
}