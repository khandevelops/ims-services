package com.usdtl.ims.requestMaster.requestMasterGeneral;

import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedDepartmentRequest;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedDepartmentResponse;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedResponse;
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
    public Page<RequestMasterGeneralEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }

    @GetMapping("list/pending")
    public Page<RequestMasterGeneralEntity> getPendingItems(@RequestParam Integer page) {
        return service.getPendingItems(page);
    }

    @GetMapping("list/complete")
    public Page<RequestMasterGeneralEntity> getCompleteItems(@RequestParam Integer page) {
        return service.getCompleteItems(page);
    }

    @PostMapping("list/create")
    public List<RequestMasterGeneralEntity> createItems(@RequestBody List<RequestMasterGeneralEntity> requests) {
        return service.createItems(requests);
    }

    @PatchMapping(path = "{id}/update")
    public RequestMasterGeneralEntity updateItem(@PathVariable Integer id, @RequestBody RequestMasterGeneralEntity requestItems) {
        return service.updateItem(id, requestItems);
    }

    @PatchMapping(path = "list/update")
    public List<RequestMasterGeneralEntity> updateItems(@RequestBody List<RequestMasterGeneralEntity> requestItems) {
        return service.updateItems(requestItems);
    }

    @PatchMapping(path = "confirm")
    public List<RequestMasterGeneralEntity> confirmItems(@RequestBody List<RequestMasterGeneralEntity> request) {
        return service.confirmItems(request);
    }
}
