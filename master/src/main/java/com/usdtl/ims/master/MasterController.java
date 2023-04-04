package com.usdtl.ims.master;

import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor

public class MasterController {
    private MasterService service;

    @GetMapping("list/filter")
    public Page<MasterEntity> getItemsFiltered(@RequestParam String item, @RequestParam Integer page) {
        return service.getItemsFiltered(item, page);
    }

    @GetMapping("list")
    public Page<MasterEntity> getItemsByPage(@RequestParam Integer page) {
        return service.getItemsByPage(page);
    }

    @GetMapping(path = "{id}")
    public  ResponseEntity<MasterEntity> getItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MasterEntity> createItem(@RequestBody MasterRequest request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }

    @PatchMapping(path = "{id}")
    public MasterEntity updateItemById(@PathVariable(value = "id") Integer id, @RequestBody MasterRequest request) {
        return service.updateItemById(id, request);
    }

    @DeleteMapping(path = "{id}")
    public void deleteItemById(@PathVariable(value = "id")Integer id) {
        service.deleteItemById(id);
    }
}
