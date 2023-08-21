package com.usdtl.ims.departmentMaster.qcInternalStandardsMaster;

import com.usdtl.ims.clients.responseRecord.MasterDepartmentClient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class QcInternalStandardsService {
    private QcInternalStandardsRepository qcInternalStandardsRepository;
    private MasterDepartmentClient client;

    public Page<QcInternalStandardsEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return qcInternalStandardsRepository.findAll(pageRequest);
    }

    public Double getTotal() {
        return qcInternalStandardsRepository.getGrandTotal();
    }
}