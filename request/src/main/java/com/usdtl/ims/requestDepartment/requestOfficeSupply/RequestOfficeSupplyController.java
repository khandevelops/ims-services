package com.usdtl.ims.requestDepartment.requestOfficeSupply;

import com.usdtl.ims.requestDepartment.request.RequestDepartmentResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("office-supply")
public class RequestOfficeSupplyController {

    private RequestOfficeSupplyService service;

    @GetMapping("list/transformed")
    public Page<RequestOfficeSupplyEntity> getRequestTranformedItemsByPage(@RequestParam Integer page) {
        return service.getItems(page);
    }
}
