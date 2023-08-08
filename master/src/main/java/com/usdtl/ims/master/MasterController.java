package com.usdtl.ims.master;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.ims.master.requests.AssignRequest;
import com.usdtl.ims.master.responses.AssignResponse;
import com.usdtl.ims.master.responses.DeleteResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor

public class MasterController {
    private MasterService service;
    @GetMapping("filter")
    public Page<MasterEntity> getItemsByKeyword(@RequestParam String keyword, @RequestParam Integer page) {
        return service.getItemsByKeyword(keyword, page);
    }
    @GetMapping("sort")
    public Page<MasterEntity> getItemsByPage(@RequestParam Integer page, @RequestParam String column, @RequestParam String direction) {
        return service.sorItems(page, column, direction);
    }
    @GetMapping("list")
    public Page<MasterEntity> getItemsByPage(@RequestParam Integer page) {
        return service.getItems(page);
    }

    @GetMapping(path = "{id}")
    public  ResponseEntity<MasterEntity> getItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MasterEntity> createItem(@RequestBody MasterEntity request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }

    @PatchMapping(path = "{id}/update")
    public MasterEntity updateItem(@PathVariable(value = "id") Integer id, @RequestBody MasterEntity request) {
        return service.updateItem(id, request);
    }

    @DeleteMapping(path = "{id}/delete")
    public ResponseEntity<DeleteResponse> deleteItemById(@PathVariable(value = "id")Integer id) {
        return service.deleteItemById(id);
    }
}
