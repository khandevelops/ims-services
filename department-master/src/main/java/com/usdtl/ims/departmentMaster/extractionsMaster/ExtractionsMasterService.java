package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.departmentMaster.common.MasterEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExtractionsMasterService {
    private ExtractionsMasterRepository repository;

    public Page<ExtractionsMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }
    public ExtractionsMasterEntity createDepartmentMasterItem(MasterEntity masterItem) {
        ExtractionsMasterEntity newDepartmentItems = ExtractionsMasterEntity.builder().build();
        newDepartmentItems.setMasterItem(masterItem);
        repository.save(newDepartmentItems);
        return newDepartmentItems;
    }
    public Double getTotal() {
        return repository.getGrandTotal();
    }

}