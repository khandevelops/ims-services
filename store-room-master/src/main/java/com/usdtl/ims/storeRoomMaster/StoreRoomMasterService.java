package com.usdtl.ims.storeRoomMaster;

import com.usdtl.ims.clients.StoreRoomMasterTransformedResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreRoomMasterService {

    private StoreRoomMasterRepository repository;

    public Page<StoreRoomMasterEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }
}
