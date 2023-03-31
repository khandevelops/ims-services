package com.usdtl.inventory.masterDepartment.masterRd;

import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterRdService {
    private MasterRdRepository repository;

    public MasterRdEntity getItemById(Integer id) throws NotFoundException {
        MasterRdEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }
}
