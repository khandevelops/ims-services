package com.usdtl.ims.departmentMaster.storeRoom;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface StoreRoomMasterRepository extends PagingAndSortingRepository<StoreRoomMasterEntity, Integer> {
}
