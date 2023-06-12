package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.clients.MasterDepartmentClient;
import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExtractionsMasterService {
    private ExtractionsMasterRepository repository;
    private MasterDepartmentClient client;

    public Page<DepartmentMasterResponse> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }

}