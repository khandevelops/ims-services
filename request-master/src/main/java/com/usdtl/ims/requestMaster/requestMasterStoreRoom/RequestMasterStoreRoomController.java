package com.usdtl.ims.requestMaster.requestMasterStoreRoom;

import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedDepartmentRequest;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedDepartmentResponse;
import com.usdtl.ims.requestMaster.requestMasterStoreRoom.RequestMasterStoreRoomEntity;
import com.usdtl.ims.requestMaster.requestMasterGeneral.RequestMasterGeneralEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("store-room")
public class RequestMasterStoreRoomController {

    private RequestMasterStoreRoomService service;

    @GetMapping("list")
    public Page<RequestMasterStoreRoomEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }

    @GetMapping("list/pending")
    public Page<RequestMasterStoreRoomEntity> getPendingItems(@RequestParam Integer page) {
        return service.getPendingItems(page);
    }

    @GetMapping("list/complete")
    public Page<RequestMasterStoreRoomEntity> getCompleteItems(@RequestParam Integer page) {
        return service.getPendingItems(page);
    }

    @PostMapping("list/create")
    public List<RequestMasterStoreRoomEntity> createItems(@RequestBody List<RequestMasterStoreRoomEntity> requests) {
        return service.createItems(requests);
    }

    @PatchMapping(path = "{id}/update")
    public RequestMasterStoreRoomEntity updateItem(@PathVariable Integer id, @RequestBody RequestMasterStoreRoomEntity requestItems) {
        return service.updateItem(id, requestItems);
    }

    @PatchMapping(path = "update/items")
    public List<RequestMasterStoreRoomEntity> updateItems(@RequestBody List<RequestMasterStoreRoomEntity> requestItems) {
        return service.updateItems(requestItems);
    }

    @PatchMapping(path = "confirm")
    public List<RequestMasterStoreRoomEntity> confirmRequestItems(@RequestBody List<RequestMasterStoreRoomEntity> request) {
        return service.confirmRequestItems(request);
    }
}
