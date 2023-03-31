package com.usdtl.ims.requestMaster.requestMasterOfficeSupply;

import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedAdminRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("office-supply")
public class RequestMasterOfficeSupplyController {

    private RequestMasterOfficeSupplyService service;

    @GetMapping("list")
    public Page<RequestMasterOfficeSupplyEntity> getRequestItemsByPage(@RequestParam Integer page) {
        return service.getRequestItemsByPage(page);
    }

    @GetMapping("list/transformed/admin")
    public Page<RequestMasterTransformedAdminRequest> getRequestMasterTransformedItemsByPage(@RequestParam Integer page) {
        return service.getRequestMasterTransformedItemsByPage(page);
    }

    @GetMapping("list/transformed/department")
    public Page<RequestMasterTransformedAdminRequest> getRequestMasterTransformedDepartmentItemsByPage(@RequestParam Integer page) {
        return service.getRequestMasterTransformedItemsByPage(page);
    }

    @GetMapping("list/completed")
    public Page<RequestMasterOfficeSupplyEntity> getRequestCompletedItemsByPage(@RequestParam Integer page) {
        return service.getRequestCompletedItemsByPage(page);
    }

    @GetMapping("list/pending")
    public Page<RequestMasterOfficeSupplyEntity> getRequestPendingItemsByPage(@RequestParam Integer page) {
        return service.getRequestPendingItemsByPage(page);
    }

    @PostMapping("create")
    public ResponseEntity<String> createRequestItem(@RequestBody List<RequestMasterOfficeSupplyEntity> requests) {
        return new ResponseEntity<>(service.createRequestItem(requests), HttpStatus.CREATED);
    }

    @PutMapping(path = "update")
    public RequestMasterOfficeSupplyEntity updateRequestItem(@RequestBody RequestMasterOfficeSupplyEntity request) {
        return service.updateRequestItem(request);
    }

    @PutMapping(path = "confirm")
    public List<RequestItemRequest> confirmRequestItems(@RequestBody List<RequestItemRequest> request) {
        return service.confirmRequestItems(request);
    }
}
