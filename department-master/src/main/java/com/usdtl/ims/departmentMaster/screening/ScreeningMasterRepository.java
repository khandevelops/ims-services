package com.usdtl.ims.departmentMaster.screening;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface ScreeningMasterRepository extends PagingAndSortingRepository<ScreeningMasterEntity, Integer> {
    @Query(value = "SELECT ROUND(SUM(quantity * unit_price), 2) FROM inventory.screening JOIN inventory.master on inventory.screening.item_id = inventory.master.id", nativeQuery = true)
    Double getGrandTotal();
}
