package com.usdtl.ims.departmentMaster.specimenProcessing;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface SpecimenProcessingMasterRepository extends PagingAndSortingRepository<SpecimenProcessingMasterEntity, Integer> {
    @Query(value = "SELECT ROUND(SUM(quantity * unit_price), 2) FROM inventory.specimen_processing JOIN inventory.master on inventory.specimen_processing.item_id = inventory.master.id", nativeQuery = true)
    Double getGrandTotal();
}
