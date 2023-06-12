package com.usdtl.ims.departmentMaster.extractions;

import com.usdtl.ims.clients.MasterDepartmentClient;
import com.usdtl.ims.departmentMaster.common.response.DepartmentMasterResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ExtractionsMasterService {
    private ExtractionsMasterRepository extractionsMasterRepository;
    private MasterDepartmentClient client;

    public Page<ExtractionsMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return extractionsMasterRepository.findAll(pageRequest);
    }

}