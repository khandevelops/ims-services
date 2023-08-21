package com.usdtl.ims.clients.departments;
import com.usdtl.ims.clients.departments.common.response.ExtractionsResponse;
import com.usdtl.ims.clients.responseRecord.DepartmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "departments")
public interface ExtractionsClient {
    @PostMapping("/ims/api/v1/departments/extractions/create")
    public ExtractionsResponse createItem(@RequestBody ExtractionsResponse request);

    @PutMapping("/ims/api/v1/departments/extractions/{id}")
    public List<DepartmentResponse> updateQuantity (
            @PathVariable("id") Integer Id,
            @RequestBody List<DepartmentResponse> response);
}
