package com.usdtl.ims.departmentMaster.shipping;

import com.usdtl.ims.clients.responseRecord.MasterDepartmentClient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ShippingMasterService {
    private ShippingMasterRepository shippingMasterRepository;
    private MasterDepartmentClient client;

    public Page<ShippingMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return shippingMasterRepository.findAll(pageRequest);
    }

    public Double getTotal() {
        return shippingMasterRepository.getGrandTotal();
    }
}