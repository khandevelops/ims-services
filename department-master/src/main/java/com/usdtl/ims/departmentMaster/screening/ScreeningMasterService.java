package com.usdtl.ims.departmentMaster.screening;

import com.usdtl.ims.clients.responseRecord.MasterDepartmentClient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ScreeningMasterService {
    private ScreeningMasterRepository screeningMasterRepository;
    private MasterDepartmentClient client;

    public Page<ScreeningMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return screeningMasterRepository.findAll(pageRequest);
    }

    public Double getTotal() {
        return screeningMasterRepository.getGrandTotal();
    }
}