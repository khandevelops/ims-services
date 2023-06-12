package com.usdtl.ims.departmentMaster.storeRoom;

import com.usdtl.ims.clients.StoreRoomTransformedResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StoreRoomController {

    private StoreRoomService service;

    @GetMapping("list")
    public Page<StoreRoomEntity> getStoreRoomItemsByPage(@RequestParam Integer page) {
        return service.getStoreRoomItemsByPage(page);
    }

    @GetMapping("list/transformed")
    public Page<StoreRoomTransformedResponse> getStoreRoommasterItemsByPage(@RequestParam Integer page) {
        return service.getStoreRoomMasterItemsByPage(page);
    }
}
