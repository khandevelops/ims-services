package com.usdtl.inventory.masterDepartment.masterMassSpec;

import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterMassSpecService {
    private MasterMassSpecRepository repository;

    public MasterMassSpecEntity getItemById(Integer id) throws NotFoundException {
        MasterMassSpecEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }
}
