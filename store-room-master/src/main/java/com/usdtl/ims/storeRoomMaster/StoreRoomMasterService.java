package com.usdtl.ims.storeRoomMaster;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreRoomMasterService {

    private StoreRoomMasterRepository repository;

    public Page<StoreRoomMasterEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }
}
