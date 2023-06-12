package com.usdtl.ims.departmentMaster.storeRoomMaster;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRoomMasterRepository extends PagingAndSortingRepository<StoreRoomMasterEntity, Integer> {
}
