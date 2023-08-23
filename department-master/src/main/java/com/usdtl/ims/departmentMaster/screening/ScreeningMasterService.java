package com.usdtl.ims.departmentMaster.screening;

import com.usdtl.ims.clients.responseRecord.MasterDepartmentClient;
import com.usdtl.ims.departmentMaster.common.MasterEntity;
import com.usdtl.ims.departmentMaster.extractionsMaster.ExtractionsMasterEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ScreeningMasterService {
    private ScreeningMasterRepository repository;

    public Page<ScreeningMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }
    public ScreeningMasterEntity createDepartmentMasterItem(MasterEntity masterItem) {
        ScreeningMasterEntity newDepartmentItems = ScreeningMasterEntity.builder().build();
        newDepartmentItems.setMasterItem(masterItem);
        repository.save(newDepartmentItems);
        return newDepartmentItems;
    }
    public Double getTotal() {
        return repository.getGrandTotal();
    }
}