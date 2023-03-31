package com.usdtl.ims.requestDepartment.requestGeneral;

import com.usdtl.ims.clients.RequestItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("general")
public class RequestGeneralController {

    private RequestGeneralService service;

    @GetMapping("list")
    public Page<RequestGeneralEntity> getRequestItemsByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return service.getRequestItemsByPage(page, size);
    }

    @GetMapping("list/completed")
    public Page<RequestGeneralEntity> getRequestCompletedItemsByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return service.getRequestCompletedItemsByPage(page, size);
    }

    @GetMapping("list/pending")
    public Page<RequestGeneralEntity> getRequestPendingItemsByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return service.getRequestPendingItemsByPage(page, size);
    }

    @PostMapping("create")
    public ResponseEntity<String> createRequestItem(@RequestBody List<RequestGeneralEntity> requests) {
        return new ResponseEntity<>(service.createRequestItem(requests), HttpStatus.CREATED);
    }

    @PutMapping(path = "update")
    public RequestGeneralEntity updateRequestItem(@RequestBody RequestGeneralEntity request) {
        return service.updateRequestItem(request);
    }

    @PutMapping(path = "confirm")
    public List<RequestItemRequest> confirmRequestItems(@RequestBody List<RequestGeneralEntity> request) {
        return service.confirmRequestItems(request);
    }
}
