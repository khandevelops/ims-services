package com.usdtl.ims.departmentMaster.requestMaster.requestMasterGeneral;

import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.departmentMaster.requestMaster.request.RequestMasterTransformedDepartmentRequest;
import com.usdtl.ims.departmentMaster.requestMaster.request.RequestMasterTransformedDepartmentResponse;
import com.usdtl.ims.departmentMaster.requestMaster.request.RequestMasterTransformedResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("general")
public class RequestMasterGeneralController {

    private RequestMasterGeneralService service;

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
    public RequestMasterGeneralEntity updateRequestItem(@PathVariable Integer id, @RequestBody RequestMasterTransformedDepartmentRequest requestItems) {
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
