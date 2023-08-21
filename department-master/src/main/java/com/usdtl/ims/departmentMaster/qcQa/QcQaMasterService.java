package com.usdtl.ims.departmentMaster.qcQa;

import com.usdtl.ims.clients.responseRecord.MasterDepartmentClient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QcQaMasterService {
    private QcQaMasterRepository qcQaMasterRepository;
    private MasterDepartmentClient client;

    public Page<QcQaMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return qcQaMasterRepository.findAll(pageRequest);
    }

    public Double getTotal() {
        return qcQaMasterRepository.getGrandTotal();
    }
}