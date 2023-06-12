package com.usdtl.inventory.masterDepartment.masterStoreRoom.masterScreening;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterStoreRoomRepository extends PagingAndSortingRepository<MasterStoreRoomEntity, Integer> {
    Page<MasterStoreRoomEntity> findByDepartmentItemsIsNotEmpty(Pageable pageable);
}
