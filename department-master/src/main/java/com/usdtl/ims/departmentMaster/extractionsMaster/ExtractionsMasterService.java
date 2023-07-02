package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.clients.MasterDepartmentClient;
import com.usdtl.ims.departmentMaster.common.GrandTotal;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExtractionsMasterService {
    private ExtractionsMasterRepository extractionsMasterRepository;
    private MasterDepartmentClient client;

    public Page<ExtractionsMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return extractionsMasterRepository.findAll(pageRequest);
    }

    public Double getTotal() {
        return extractionsMasterRepository.getGrandTotal();
    }

}