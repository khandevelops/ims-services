package com.usdtl.ims.departmentMaster.storeRoom;

import com.usdtl.ims.clients.MasterDepartmentClient;
import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class StoreRoomMasterService {
    private StoreRoomMasterRepository storeRoomMasterRepository;
    private MasterDepartmentClient client;

    public Page<StoreRoomMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return storeRoomMasterRepository.findAll(pageRequest);
    }

    public Double getTotal() {
        return storeRoomMasterRepository.getGrandTotal();
    }
}