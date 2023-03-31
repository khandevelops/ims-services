package com.usdtl.ims.requestDepartment.requestOfficeSupply;

import com.usdtl.ims.clients.RequestItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("office-supply")
public class RequestOfficeSupplyController {

    private RequestOfficeSupplyService service;

    @GetMapping("list")
    public Page<RequestOfficeSupplyEntity> getRequestItemsByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return service.getRequestItemsByPage(page, size);
    }

    @GetMapping("list/completed")
    public Page<RequestOfficeSupplyEntity> getRequestCompletedItemsByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return service.getRequestCompletedItemsByPage(page, size);
    }

    @GetMapping("list/pending")
    public Page<RequestOfficeSupplyEntity> getRequestPendingItemsByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return service.getRequestPendingItemsByPage(page, size);
    }

    @PostMapping("create")
    public ResponseEntity<String> createRequestItem(@RequestBody List<RequestOfficeSupplyEntity> requests) {
        return new ResponseEntity<>(service.createRequestItem(requests), HttpStatus.CREATED);
    }

    @PutMapping(path = "update")
    public RequestOfficeSupplyEntity updateRequestItem(@RequestBody RequestOfficeSupplyEntity request) {
        return service.updateRequestItem(request);
    }

    @PutMapping(path = "confirm")
    public List<RequestItemRequest> confirmRequestItems(@RequestBody List<RequestItemRequest> request) {
        return service.confirmRequestItems(request);
    }
}
