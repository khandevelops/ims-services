package com.usdtl.inventory.masterDepartment.masterShipping;

import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterShippingService {
    private MasterShippingRepository repository;

    public MasterShippingEntity getItemById(Integer id) throws NotFoundException {
        MasterShippingEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }
}
