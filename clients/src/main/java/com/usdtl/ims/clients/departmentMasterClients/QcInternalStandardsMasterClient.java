package com.usdtl.ims.clients.departmentMasterClients;

import com.usdtl.ims.clients.departmentMasterClients.common.response.DepartmentMasterResponse;
import com.usdtl.ims.clients.masterClients.response.MasterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "department-master")
public interface QcInternalStandardsMasterClient {
    @PostMapping("/ims/api/v1/department-master/qc-internal-standards/create")
    public DepartmentMasterResponse createDepartmentMasterItem(
            @RequestBody MasterResponse masterItem
    );

    @GetMapping("/ims/api/v1/department-master/qc-internal-standards/scheduled/email/list")
    List<DepartmentMasterResponse> getEmailItems();
}
