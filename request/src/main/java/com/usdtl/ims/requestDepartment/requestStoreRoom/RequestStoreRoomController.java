package com.usdtl.ims.requestDepartment.requestStoreRoom;

import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.requestDepartment.request.RequestDepartmentResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("store-room")
public class RequestStoreRoomController {

    private RequestStoreRoomService service;

    @GetMapping("list/transformed")
    public Page<RequestDepartmentResponse> getRequestTranformedItemsByPage(@RequestParam Integer page) {
        return service.getRequestTranformedItemsByPage(page);
    }

//    @PostMapping("create")
//    public ResponseEntity<List<RequestStoreRoomEntity>> createRequestItem(@RequestBody List<RequestStoreRoomEntity> requests) {
//        return new ResponseEntity<>(service.createRequestItem(requests), HttpStatus.CREATED);
//    }
//
//    @PutMapping(path = "update")
//    public RequestStoreRoomEntity updateRequestItem(@RequestBody RequestStoreRoomEntity request) {
//        return service.updateRequestItem(request);
//    }
}
