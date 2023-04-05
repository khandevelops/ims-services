package com.usdtl.ims.requestMaster.requestMasterOfficeSupply;

import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedAdminResponse;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedDepartmentRequest;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedDepartmentResponse;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedResponse;
import com.usdtl.ims.requestMaster.requestMasterGeneral.RequestMasterGeneralEntity;
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

    @GetMapping("list/transformed")
    public Page<RequestMasterTransformedResponse> getRequestMasterTransformedItemsByPage(@RequestParam Integer page) {
        return service.getRequestMasterTransformedItemsByPage(page);
    }

    @GetMapping("list/transformed/pending")
    public Page<RequestMasterTransformedDepartmentResponse> getRequestMasterPendingTransformedItemsByPage(@RequestParam Integer page) {
        return service.getRequestMasterPendingTransformedItemsByPage(page);
    }

    @GetMapping("list/transformed/complete")
    public Page<RequestMasterTransformedDepartmentResponse> getRequestMasterCompleteTransformedItemsByPage(@RequestParam Integer page) {
        return service.getRequestMasterCompleteTransformedItemsByPage(page);
    }

    @PostMapping("create")
    public ResponseEntity<List<RequestMasterTransformedResponse>> createRequestItem(@RequestBody List<RequestMasterTransformedDepartmentRequest> requests) {
        return new ResponseEntity<>(service.createRequestItem(requests), HttpStatus.CREATED);
    }

    @PatchMapping(path = "update/item")
    public RequestMasterOfficeSupplyEntity updateRequestItem(@PathVariable Integer id, @RequestBody RequestMasterTransformedDepartmentRequest requestItems) {
        return service.updateRequestItem(id, requestItems);
    }

    @PatchMapping(path = "update/items")
    public List<RequestMasterTransformedDepartmentResponse> updateRequestItems(@RequestBody List<RequestMasterTransformedDepartmentRequest> requestItems) {
        return service.updateRequestItems(requestItems);
    }

    @PatchMapping(path = "confirm")
    public List<RequestItemRequest> confirmRequestItems(@RequestBody List<RequestMasterGeneralEntity> request) {
        return service.confirmRequestItems(request);
    }
}