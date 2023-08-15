package com.usdtl.ims.requestMaster.requestMasterOfficeSupply;

import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedDepartmentRequest;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedDepartmentResponse;
import com.usdtl.ims.requestMaster.requestMasterGeneral.RequestMasterGeneralEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("office-supply")
public class RequestMasterOfficeSupplyController {

    private RequestMasterOfficeSupplyService service;

    @GetMapping("list")
    public Page<RequestMasterOfficeSupplyEntity> getItems(@RequestParam Integer page) {
        return service.getItems(page);
    }

    @GetMapping("list/pending")
    public Page<RequestMasterOfficeSupplyEntity> getPendingItems(@RequestParam Integer page) {
        return service.getPendingItems(page);
    }

    @GetMapping("list/complete")
    public Page<RequestMasterOfficeSupplyEntity> getCompleteItems(@RequestParam Integer page) {
        return service.getPendingItems(page);
    }

    @PostMapping("list/create")
    public List<RequestMasterOfficeSupplyEntity> createItem(@RequestBody List<RequestMasterOfficeSupplyEntity> requests) {
        return service.createItems(requests);
    }

    @PatchMapping(path = "{id}/update")
    public RequestMasterOfficeSupplyEntity updateItem(@PathVariable Integer id, @RequestBody RequestMasterOfficeSupplyEntity requestItems) {
        return service.updateItem(id, requestItems);
    }

    @PatchMapping(path = "update/items")
    public List<RequestMasterOfficeSupplyEntity> updateItems(@RequestBody List<RequestMasterOfficeSupplyEntity> requestItems) {
        return service.updateItems(requestItems);
    }

    @PatchMapping(path = "confirm")
    public List<RequestMasterOfficeSupplyEntity> confirmItems(@RequestBody List<RequestMasterOfficeSupplyEntity> request) {
        return service.confirmItems(request);
    }
}
