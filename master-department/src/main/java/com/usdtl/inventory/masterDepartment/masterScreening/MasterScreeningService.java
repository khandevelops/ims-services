package com.usdtl.inventory.masterDepartment.masterScreening;

import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterScreeningService {
    private MasterScreeningRepository repository;

    public MasterScreeningEntity getItemById(Integer id) throws NotFoundException {
        MasterScreeningEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }
}
