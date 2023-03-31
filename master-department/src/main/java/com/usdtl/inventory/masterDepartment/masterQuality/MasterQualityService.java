package com.usdtl.inventory.masterDepartment.masterQuality;

import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterQualityService {
    private MasterQualityRepository repository;

    public MasterQualityEntity getItemById(Integer id) throws NotFoundException {
        MasterQualityEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }
}
