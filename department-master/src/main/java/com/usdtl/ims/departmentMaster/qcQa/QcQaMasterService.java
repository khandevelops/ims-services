package com.usdtl.ims.departmentMaster.qcQa;

import com.usdtl.ims.clients.responseRecord.MasterDepartmentClient;
import com.usdtl.ims.departmentMaster.common.MasterEntity;
import com.usdtl.ims.departmentMaster.extractionsMaster.ExtractionsMasterEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QcQaMasterService {
    private QcQaMasterRepository repository;

    public Page<QcQaMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }
    public QcQaMasterEntity createDepartmentMasterItem(MasterEntity masterItem) {
        QcQaMasterEntity newDepartmentItems = QcQaMasterEntity.builder().build();
        newDepartmentItems.setMasterItem(masterItem);
        repository.save(newDepartmentItems);
        return newDepartmentItems;
    }
    public Double getTotal() {
        return repository.getGrandTotal();
    }
}