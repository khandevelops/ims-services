package com.usdtl.ims.departmentMaster.storeRoom;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("store-room")
public class StoreRoomMasterController {
    private StoreRoomMasterService storeRoomMasterService;

    @GetMapping("list")
    public Page<StoreRoomMasterEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return storeRoomMasterService.getDepartmentMasterItems(page);
    }

    @GetMapping("grand-total")
    public Double getTotal() {
        return storeRoomMasterService.getTotal();
    }
}