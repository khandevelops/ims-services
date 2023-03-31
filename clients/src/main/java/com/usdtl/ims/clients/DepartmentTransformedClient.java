package com.usdtl.ims.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "department-master")
public interface DepartmentTransformedClient {
    @GetMapping("/ims/api/v1/department-master/extractions/scheduled/email/list")
    List<DepartmentTransformedRequest> getScheduledEmailItems();
}
