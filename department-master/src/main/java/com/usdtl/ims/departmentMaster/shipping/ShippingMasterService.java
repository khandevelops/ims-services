package com.usdtl.ims.departmentMaster.shipping;

import com.usdtl.ims.clients.responseRecord.MasterDepartmentClient;
import com.usdtl.ims.departmentMaster.common.MasterEntity;
import com.usdtl.ims.departmentMaster.extractionsMaster.ExtractionsMasterEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ShippingMasterService {
    private ShippingMasterRepository repository;

    public Page<ShippingMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }
    public ShippingMasterEntity createDepartmentMasterItem(MasterEntity masterItem) {
        ShippingMasterEntity newDepartmentItems = ShippingMasterEntity.builder().build();
        newDepartmentItems.setMasterItem(masterItem);
        repository.save(newDepartmentItems);
        return newDepartmentItems;
    }
    public Double getTotal() {
        return repository.getGrandTotal();
    }
}