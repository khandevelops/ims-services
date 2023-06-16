package com.usdtl.ims.storeRoom;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRoomRepository extends PagingAndSortingRepository<StoreRoomEntity, Integer> {
}
