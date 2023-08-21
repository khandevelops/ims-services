package com.usdtl.ims.departmentMaster.rd;

import com.usdtl.ims.clients.responseRecord.MasterDepartmentClient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class RdMasterService {
    private RdMasterRepository rdMasterRepository;
    private MasterDepartmentClient client;

    public Page<RdMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return rdMasterRepository.findAll(pageRequest);
    }

    public Double getTotal() {
        return rdMasterRepository.getGrandTotal();
    }
}