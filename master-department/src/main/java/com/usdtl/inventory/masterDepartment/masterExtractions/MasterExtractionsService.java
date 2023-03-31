package com.usdtl.inventory.masterDepartment.masterExtractions;

import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterExtractionsService {
    private MasterExtractionsRepository repository;

    public MasterExtractionsEntity getItemById(Integer id) throws NotFoundException {
        MasterExtractionsEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }
}
