package com.usdtl.ims.downloads.master;

import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterService {
    private MasterRepository repository;

    public MasterEntity getItemById(Integer id) throws NotFoundException {
        MasterEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }
}
