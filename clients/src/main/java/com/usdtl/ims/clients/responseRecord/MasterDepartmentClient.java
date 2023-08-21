package com.usdtl.ims.clients.responseRecord;

import com.usdtl.ims.clients.MasterDepartmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "master-department")
public interface MasterDepartmentClient {
    @GetMapping("/ims/api/v1/master-department/extractions/{id}")
    com.usdtl.ims.clients.MasterDepartmentResponse getMasterExtractionsItemById(@PathVariable("id") Integer id);

    @GetMapping("/ims/api/v1/master-department/mass-spec/{id}")
    com.usdtl.ims.clients.MasterDepartmentResponse getMasterMassSpecItemById(@PathVariable("id") Integer id);

    @GetMapping("/ims/api/v1/master-department/quality/{id}")
    com.usdtl.ims.clients.MasterDepartmentResponse getMasterQualityItemById(@PathVariable("id") Integer id);

    @GetMapping("/ims/api/v1/master-department/rd/{id}")
    com.usdtl.ims.clients.MasterDepartmentResponse getMasterRdItemById(@PathVariable("id") Integer id);

    @GetMapping("/ims/api/v1/master-department/receiving/{id}")
    com.usdtl.ims.clients.MasterDepartmentResponse getMasterReceivingItemById(@PathVariable("id") Integer id);

    @GetMapping("/ims/api/v1/master-department/screening/{id}")
    com.usdtl.ims.clients.MasterDepartmentResponse getMasterScreeningItemById(@PathVariable("id") Integer id);

    @GetMapping("/ims/api/v1/master-department/shipping/{id}")
    MasterDepartmentResponse getMasterShippingItemById(@PathVariable("id") Integer id);
}
