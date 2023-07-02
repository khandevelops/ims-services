package com.usdtl.ims.departmentMaster.specimenProcessing;

import com.usdtl.ims.clients.MasterDepartmentClient;
import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class SpecimenProcessingMasterService {
    private SpecimenProcessingMasterRepository specimenProcessingMasterRepository;
    private MasterDepartmentClient client;

    public Page<SpecimenProcessingMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return specimenProcessingMasterRepository.findAll(pageRequest);
    }

    public Double getTotal() {
        return specimenProcessingMasterRepository.getGrandTotal();
    }
}