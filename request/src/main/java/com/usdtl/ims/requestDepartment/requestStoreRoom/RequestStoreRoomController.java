package com.usdtl.ims.requestDepartment.requestStoreRoom;

import com.usdtl.ims.clients.RequestItemRequest;
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

    @GetMapping("list")
    public Page<RequestStoreRoomEntity> getRequestItemsByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return service.getRequestItemsByPage(page, size);
    }

    @GetMapping("list/completed")
    public Page<RequestStoreRoomEntity> getRequestCompletedItemsByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return service.getRequestCompletedItemsByPage(page, size);
    }

    @GetMapping("list/pending")
    public Page<RequestStoreRoomEntity> getRequestPendingItemsByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return service.getRequestPendingItemsByPage(page, size);
    }

    @PostMapping("create")
    public ResponseEntity<List<RequestStoreRoomEntity>> createRequestItem(@RequestBody List<RequestStoreRoomEntity> requests) {
        return new ResponseEntity<>(service.createRequestItem(requests), HttpStatus.CREATED);
    }

    @PutMapping(path = "update")
    public RequestStoreRoomEntity updateRequestItem(@RequestBody RequestStoreRoomEntity request) {
        return service.updateRequestItem(request);
    }

    @PutMapping(path = "confirm")
    public List<RequestItemRequest> confirmRequestItems(@RequestBody List<RequestItemRequest> request) {
        return service.confirmRequestItems(request);
    }
}
