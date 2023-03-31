package com.usdtl.ims.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "departments")
public interface ExtractionsClient {
    @PutMapping("/ims/api/v1/departments/extractions/{id}")
    public List<DepartmentResponse> updateQuantity (
            @PathVariable("id") Integer Id,
            @RequestBody List<DepartmentResponse> response);
}
