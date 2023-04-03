package com.usdtl.ims.requestDepartment.requestGeneral;

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
@RequestMapping("general")
public class RequestGeneralController {

    private RequestGeneralService service;

    @GetMapping("list/transformed")
    public Page<RequestDepartmentResponse> getRequestItemsByPage(@RequestParam Integer page) {
        return service.getRequestItemsByPage(page);
    }

//    @PostMapping("create")
//    public ResponseEntity<String> createRequestItem(@RequestBody List<RequestGeneralEntity> requests) {
//        return new ResponseEntity<>(service.createRequestItem(requests), HttpStatus.CREATED);
//    }
//
//    @PutMapping(path = "update")
//    public RequestGeneralEntity updateRequestItem(@RequestBody RequestGeneralEntity request) {
//        return service.updateRequestItem(request);
//    }
}
