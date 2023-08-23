package com.usdtl.ims.departmentMaster.massSpecMaster;

import com.usdtl.ims.clients.responseRecord.MasterDepartmentClient;
import com.usdtl.ims.departmentMaster.common.MasterEntity;
import com.usdtl.ims.departmentMaster.extractionsMaster.ExtractionsMasterEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MassSpecMasterService {
    private MassSpecMasterRepository repository;

    public Page<MassSpecMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }
    public MassSpecMasterEntity createDepartmentMasterItem(MasterEntity masterItem) {
        MassSpecMasterEntity newDepartmentItems = MassSpecMasterEntity.builder().build();
        newDepartmentItems.setMasterItem(masterItem);
        repository.save(newDepartmentItems);
        return newDepartmentItems;
    }
    public Double getTotal() {
        return repository.getGrandTotal();
    }
}