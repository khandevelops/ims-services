package com.usdtl.ims.requestMaster.requestMasterGeneral;

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
@RequestMapping("general")
public class RequestMasterGeneralController {

    private RequestMasterGeneralService service;

    @GetMapping("list")
    public Page<RequestMasterGeneralEntity> getRequestMasterItemsByPage(@RequestParam Integer page) {
        return service.getRequestItemsByPage(page);
    }

    @GetMapping("list/transformed/admin")
    public Page<RequestMasterTransformedAdminRequest> getRequestMasterTransformedAdminItemsByPage(@RequestParam Integer page) {
        return service.getRequestMasterTransformedItemsByPage(page);
    }

    @GetMapping("list/transformed/department")
    public Page<RequestMasterTransformedAdminRequest> getRequestMasterTransformedDepartmentItemsByPage(@RequestParam Integer page) {
        return service.getRequestMasterTransformedItemsByPage(page);
    }

    @GetMapping("list/completed")
    public Page<RequestMasterGeneralEntity> getRequestCompletedItemsByPage(@RequestParam Integer page) {
        return service.getRequestCompletedItemsByPage(page);
    }

    @GetMapping("list/pending")
    public Page<RequestMasterGeneralEntity> getRequestPendingItemsByPage(@RequestParam Integer page) {
        return service.getRequestPendingItemsByPage(page);
    }

    @PostMapping("create")
    public ResponseEntity<String> createRequestItem(@RequestBody List<RequestMasterGeneralEntity> requests) {
        return new ResponseEntity<>(service.createRequestItem(requests), HttpStatus.CREATED);
    }

    @PutMapping(path = "update")
    public RequestMasterGeneralEntity updateRequestItem(@RequestBody RequestMasterGeneralEntity request) {
        return service.updateRequestItem(request);
    }

    @PutMapping(path = "confirm")
    public List<RequestItemRequest> confirmRequestItems(@RequestBody List<RequestMasterGeneralEntity> request) {
        return service.confirmRequestItems(request);
    }
}
