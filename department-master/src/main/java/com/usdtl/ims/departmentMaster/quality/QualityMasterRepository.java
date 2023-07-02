package com.usdtl.ims.departmentMaster.quality;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface QualityMasterRepository extends PagingAndSortingRepository<QualityMasterEntity, Integer> {
    @Query(value = "SELECT ROUND(SUM(quantity * unit_price), 2) FROM inventory.quality JOIN inventory.master on inventory.quality.item_id = inventory.master.id", nativeQuery = true)
    Double getGrandTotal();
}
