package com.usdtl.inventory.masterDepartment.masterStoreRoom;

import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("store-room")
public class MasterStoreRoomController {
    private MasterStoreRoomService service;

    @GetMapping(path = "{id}")
    public  ResponseEntity<MasterStoreRoomEntity> getMasterStoreRoomItemById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getItemById(id), HttpStatus.OK);
    }
}
