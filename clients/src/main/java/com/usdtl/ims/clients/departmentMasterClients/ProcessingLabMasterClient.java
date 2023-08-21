package com.usdtl.ims.clients.departmentMasterClients;

import com.usdtl.ims.clients.departmentMasterClients.common.response.DepartmentMasterResponse;
import com.usdtl.ims.clients.masterClients.response.MasterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "department-master")
public interface ProcessingLabMasterClient {
    @PostMapping("/ims/api/v1/department-master/processing-lab/create")
    public DepartmentMasterResponse createDepartmentMasterItem(
            @RequestBody MasterResponse masterItem
    );

    @GetMapping("/ims/api/v1/department-master/processing-lab/scheduled/email/list")
    List<DepartmentMasterResponse> getEmailItems();
}
