package com.usdtl.ims.departmentMaster.quality;

import com.usdtl.ims.clients.MasterDepartmentClient;
import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QualityMasterService {
    private QualityMasterRepository qualityMasterRepository;
    private MasterDepartmentClient client;

    public Page<QualityMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return qualityMasterRepository.findAll(pageRequest);
    }

    public Double getTotal() {
        return qualityMasterRepository.getGrandTotal();
    }
}