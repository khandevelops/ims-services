package com.usdtl.ims.departments.massSpec;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.departments.department.DepartmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("mass-spec")
@AllArgsConstructor
@RestController
public class MassSpecController  {
    private MassSpecService service;
    @GetMapping("list")
    public Page<MassSpecEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<MassSpecEntity> getItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<MassSpecEntity> createItem(@RequestBody MassSpecEntity request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
    @PatchMapping(path = "{id}")
    public MassSpecEntity updateItem(@PathVariable(value = "id") Integer id, @RequestBody MassSpecEntity request) {
        return service.updateItem(id, request);
    }
    @DeleteMapping(path = "{id}")
    public void deleteItem(@PathVariable(value = "id")Integer id) {
        service.deleteItem(id);
    }
}
