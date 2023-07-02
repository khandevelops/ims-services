package com.usdtl.ims.departmentMaster.storeRoom;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface StoreRoomMasterRepository extends PagingAndSortingRepository<StoreRoomMasterEntity, Integer> {
    @Query(value = "SELECT ROUND(SUM(quantity * unit_price), 2) FROM inventory.store_room JOIN inventory.master on inventory.store_room.item_id = inventory.master.id", nativeQuery = true)
    Double getGrandTotal();
}
