package com.usdtl.ims.profileDetails.departmentNames;

import com.usdtl.ims.profileDetails.response.DeleteResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("department-names")
public class DepartmentNameController {
    private DepartmentNameService service;

    @GetMapping("list")
    public List<DepartmentNameEntity> getDepartmentNames() {
        return service.getDepartmentNames();
    }

    @PostMapping("create")
    public DepartmentNameEntity createDepartmentName(@RequestBody DepartmentNameEntity request) {
        return service.createDepartmentName(request);
    }

    @PatchMapping("{id}/update")
    public DepartmentNameEntity updateDepartmentName(@PathVariable(value = "id") Integer id, @RequestBody DepartmentNameEntity request) {
        return service.updateDepartmentName(id, request);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<DeleteResponse> deleteDepartmentName(@PathVariable(value = "id") Integer id) {
        return service.deleteDepartmentName(id);
    }
}
