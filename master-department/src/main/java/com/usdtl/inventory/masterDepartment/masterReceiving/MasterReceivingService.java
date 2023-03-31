package com.usdtl.inventory.masterDepartment.masterReceiving;

import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterReceivingService {
    private MasterReceivingRepository repository;

    public MasterReceivingEntity getItemById(Integer id) throws NotFoundException {
        MasterReceivingEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }
}
