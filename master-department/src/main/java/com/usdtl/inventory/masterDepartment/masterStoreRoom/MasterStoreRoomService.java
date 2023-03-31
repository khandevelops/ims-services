package com.usdtl.inventory.masterDepartment.masterStoreRoom;

import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterStoreRoomService {
    private MasterStoreRoomRepository repository;

    public MasterStoreRoomEntity getItemById(Integer id) throws NotFoundException {
        MasterStoreRoomEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }
}
