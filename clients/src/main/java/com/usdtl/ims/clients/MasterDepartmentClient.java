package com.usdtl.ims.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "master-department")
public interface MasterDepartmentClient {
    @GetMapping("/ims/api/v1/master-department/extractions/{id}")
    MasterDepartmentResponse getMasterExtractionsItemById(@PathVariable("id") Integer id);

    @GetMapping("/ims/api/v1/master-department/mass-spec/{id}")
    MasterDepartmentResponse getMasterMassSpecItemById(@PathVariable("id") Integer id);

    @GetMapping("/ims/api/v1/master-department/quality/{id}")
    MasterDepartmentResponse getMasterQualityItemById(@PathVariable("id") Integer id);

    @GetMapping("/ims/api/v1/master-department/rd/{id}")
    MasterDepartmentResponse getMasterRdItemById(@PathVariable("id") Integer id);

    @GetMapping("/ims/api/v1/master-department/receiving/{id}")
    MasterDepartmentResponse getMasterReceivingItemById(@PathVariable("id") Integer id);

    @GetMapping("/ims/api/v1/master-department/screening/{id}")
    MasterDepartmentResponse getMasterScreeningItemById(@PathVariable("id") Integer id);

    @GetMapping("/ims/api/v1/master-department/shipping/{id}")
    MasterDepartmentResponse getMasterShippingItemById(@PathVariable("id") Integer id);
}
