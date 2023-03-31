package com.usdtl.ims.storeRoomMaster;

import com.usdtl.ims.storeRoomMaster.StoreRoomMasterEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRoomMasterRepository extends PagingAndSortingRepository<StoreRoomMasterEntity, Integer> {
}
