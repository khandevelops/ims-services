package com.usdtl.ims.storeRoom;

import com.usdtl.ims.clients.StoreRoomTransformedResponse;
import com.usdtl.ims.common.exceptions.common.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StoreRoomController {

    private StoreRoomService service;

    @GetMapping("list")
    public Page<StoreRoomEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<StoreRoomEntity> getItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<StoreRoomEntity> createItem(@RequestBody StoreRoomEntity request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }
    @PatchMapping(path = "/{id}/update")
    public StoreRoomEntity updateItemById(@PathVariable(value = "id") Integer id, @RequestBody StoreRoomEntity request) {
        return service.updateItem(id, request);
    }

    @PatchMapping(path = "/list/update")
    public List<StoreRoomEntity> updateItemById(@PathVariable(value = "id") Integer id, @RequestBody List<StoreRoomEntity> request) {
        return service.updateItems(request);
    }
    @DeleteMapping(path = "{id}")
    public void deleteItem(@PathVariable(value = "id")Integer id) {
        service.deleteItem(id);
    }
}
