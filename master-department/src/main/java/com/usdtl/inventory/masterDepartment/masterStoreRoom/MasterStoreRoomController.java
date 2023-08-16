package com.usdtl.inventory.masterDepartment.masterStoreRoom;

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
@RequestMapping("store-room")
public class MasterStoreRoomController {
    private MasterStoreRoomService service;

    @GetMapping(path = "{id}")
    public  ResponseEntity<MasterStoreRoomEntity> getItem(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItem(id), HttpStatus.OK);
    }

    @GetMapping("filter")
    public Page<MasterStoreRoomEntity> filterItems(@RequestParam String keyword, @RequestParam Integer page) {
        return service.filterItems(keyword, page);
    }

    @GetMapping("sort")
    public Page<MasterStoreRoomEntity> sortItems(@RequestParam Integer page, @RequestParam String column, @RequestParam String direction) {
        return service.sorItems(page, column, direction);
    }

    @GetMapping("list")
    public Page<MasterStoreRoomEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }

    @PostMapping("create/{department}")
    public MasterStoreRoomEntity create(@RequestBody MasterStoreRoomEntity request, @PathVariable(value = "department") Department department) {
        return create(request, department);
    }

    @PostMapping("assign/{id}/{department}")
    public MasterStoreRoomEntity assign(@PathVariable(value = "id") Integer id, @PathVariable(value = "department") Department department) {
        return assign(id, department);
    }
}
