package com.usdtl.ims.departmentMaster.specimenProcessing;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface SpecimenProcessingMasterRepository extends PagingAndSortingRepository<SpecimenProcessingMasterEntity, Integer> {
    @Query(
            value = "SELECT ROUND(SUM(em.quantity* em.masterItem.unitPrice), 2) " +
                    "FROM SpecimenProcessingMasterEntity em"
    )
    Double getGrandTotal();
}
