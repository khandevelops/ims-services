package com.usdtl.ims.requestDepartment.requestStoreRoom;

import com.usdtl.ims.requestDepartment.request.RequestDepartmentResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("store-room")
public class RequestStoreRoomController {

    private RequestStoreRoomService service;
    @GetMapping("list/transformed")
    public Page<RequestStoreRoomEntity> getRequestTranformedItemsByPage(@RequestParam Integer page) {
        return service.getRequestTranformedItemsByPage(page);
    }
}
