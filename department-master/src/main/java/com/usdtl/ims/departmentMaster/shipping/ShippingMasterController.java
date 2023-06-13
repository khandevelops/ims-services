package com.usdtl.ims.departmentMaster.shipping;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("shipping")
public class ShippingMasterController {
    private ShippingMasterService shippingMasterService;

    @GetMapping("list")
    public Page<ShippingMasterEntity> getItemsByPage(@RequestParam Integer page) {
        log.info("List extractions master transformed items");
        return shippingMasterService.getDepartmentMasterItems(page);
    }
}