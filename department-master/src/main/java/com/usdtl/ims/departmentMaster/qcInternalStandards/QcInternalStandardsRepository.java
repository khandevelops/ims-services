package com.usdtl.ims.departmentMaster.qcInternalStandards;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface QcInternalStandardsRepository extends PagingAndSortingRepository<QcInternalStandardsEntity, Integer> {
    @Query(value = "SELECT ROUND(SUM(quantity * unit_price), 2) FROM inventory.qc_internal_standards JOIN inventory.master on inventory.qc_internal_standards.item_id = inventory.master.id", nativeQuery = true)
    Double getGrandTotal();
}
