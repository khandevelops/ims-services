package com.usdtl.inventory.masterDepartment.masterStoreRoom;

import com.usdtl.inventory.masterDepartment.masterQcInternalStandards.QcInternalStandardsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRoomRepository extends PagingAndSortingRepository<QcInternalStandardsEntity, Integer> {
}
