package com.usdtl.ims.departmentMaster.rd;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface RdMasterRepository extends PagingAndSortingRepository<RdMasterEntity, Integer> {
    @Query(value = "SELECT ROUND(SUM(quantity * unit_price), 2) FROM inventory.rd JOIN inventory.master on inventory.rd.item_id = inventory.master.id", nativeQuery = true)
    Double getGrandTotal();
}
