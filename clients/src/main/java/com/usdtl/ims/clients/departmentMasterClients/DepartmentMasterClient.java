package com.usdtl.ims.clients.departmentMasterClients;

import com.usdtl.ims.clients.departmentMasterClients.common.response.DepartmentMasterResponse;
import com.usdtl.ims.clients.masterClients.response.MasterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "department-master")
public interface DepartmentMasterClient {
    @PostMapping("/ims/api/v1/department-master/extractions/create")
    DepartmentMasterResponse createExtractionsMasterItem(
            @RequestBody MasterResponse masterItem
    );
    @GetMapping("/ims/api/v1/department-master/extractions/scheduled/email/list")
    List<DepartmentMasterResponse> emailExtractionsItems();

    @PostMapping("/ims/api/v1/department-master/mass-spec/create")
    DepartmentMasterResponse createMassSpecMasterItem(
            @RequestBody MasterResponse masterItem
    );

    @GetMapping("/ims/api/v1/department-master/mass-spec/scheduled/email/list")
    List<DepartmentMasterResponse> emailMassSpecItems();

    @PostMapping("/ims/api/v1/department-master/processing-lab/create")
    public DepartmentMasterResponse createProcessingLabMasterItem(
            @RequestBody MasterResponse masterItem
    );

    @GetMapping("/ims/api/v1/department-master/processing-lab/scheduled/email/list")
    List<DepartmentMasterResponse> emailProcessingLabItems();

    @PostMapping("/ims/api/v1/department-master/qc-internal-standards/create")
    public DepartmentMasterResponse createQcInternalStandardsMasterItem(
            @RequestBody MasterResponse masterItem
    );

    @GetMapping("/ims/api/v1/department-master/qc-internal-standards/scheduled/email/list")
    List<DepartmentMasterResponse> emailQcInternalStandardsItems();

    @PostMapping("/ims/api/v1/department-master/qc-qa/create")
    public DepartmentMasterResponse createQcQaMasterItem(
            @RequestBody MasterResponse masterItem
    );

    @GetMapping("/ims/api/v1/department-master/qc-qa/scheduled/email/list")
    List<DepartmentMasterResponse> emailQcQaItems();

    @PostMapping("/ims/api/v1/department-master/rd/create")
    public DepartmentMasterResponse createRdMasterItem(
            @RequestBody MasterResponse masterItem
    );

    @GetMapping("/ims/api/v1/department-master/rd/scheduled/email/list")
    List<DepartmentMasterResponse> emailRdItems();

    @PostMapping("/ims/api/v1/department-master/screening/create")
    public DepartmentMasterResponse createScreeningMasterItem(
            @RequestBody MasterResponse masterItem
    );

    @GetMapping("/ims/api/v1/department-master/screening/scheduled/email/list")
    List<DepartmentMasterResponse> emailScreeningItems();

    @PostMapping("/ims/api/v1/department-master/shipping/create")
    public DepartmentMasterResponse createShippingMasterItem(
            @RequestBody MasterResponse masterItem
    );

    @GetMapping("/ims/api/v1/department-master/shipping/scheduled/email/list")
    List<DepartmentMasterResponse> emailShippingItems();
}
