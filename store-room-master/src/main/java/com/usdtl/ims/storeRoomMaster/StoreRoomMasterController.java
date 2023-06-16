package com.usdtl.ims.storeRoomMaster;

import com.usdtl.ims.clients.StoreRoomMasterTransformedResponse;
import com.usdtl.ims.storeRoomMaster.requestAndResponse.StoreRoomMasterResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StoreRoomMasterController {

    private StoreRoomMasterService service;

    @GetMapping("list")
    public Page<StoreRoomMasterEntity> getStoreRoomItemsByPage(@RequestParam Integer page) {
        return service.getItems(page);
    }
}
