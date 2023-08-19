package com.usdtl.ims.departmentMaster.processingLabMaster;

import com.usdtl.ims.clients.MasterDepartmentClient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ProcessingLabMasterService {
    private ProcessingLabMasterRepository processingLabMasterRepository;
    private MasterDepartmentClient client;

    public Page<ProcessingLabMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return processingLabMasterRepository.findAll(pageRequest);
    }

    public Double getTotal() {
        return processingLabMasterRepository.getGrandTotal();
    }
}