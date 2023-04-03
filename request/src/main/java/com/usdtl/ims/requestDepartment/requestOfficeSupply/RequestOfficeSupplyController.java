package com.usdtl.ims.requestDepartment.requestOfficeSupply;

import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.requestDepartment.request.RequestDepartmentResponse;
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

    @GetMapping("list/transformed")
    public Page<RequestDepartmentResponse> getRequestTranformedItemsByPage(@RequestParam Integer page) {
        return service.getRequestTranformedItemsByPage(page);
    }

//    @PostMapping("create")
//    public ResponseEntity<String> createRequestItem(@RequestBody List<RequestOfficeSupplyEntity> requests) {
//        return new ResponseEntity<>(service.createRequestItem(requests), HttpStatus.CREATED);
//    }
//
//    @PutMapping(path = "update")
//    public RequestOfficeSupplyEntity updateRequestItem(@RequestBody RequestOfficeSupplyEntity request) {
//        return service.updateRequestItem(request);
//    }
}
