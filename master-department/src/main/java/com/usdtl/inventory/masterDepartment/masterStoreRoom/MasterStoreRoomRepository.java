package com.usdtl.inventory.masterDepartment.masterStoreRoom;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterStoreRoomRepository extends PagingAndSortingRepository<MasterStoreRoomEntity, Integer> {
}
