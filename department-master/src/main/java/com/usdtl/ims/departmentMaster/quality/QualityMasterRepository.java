package com.usdtl.ims.departmentMaster.quality;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface QualityMasterRepository extends PagingAndSortingRepository<QualityMasterEntity, Integer> {
    @Query(
            value = "SELECT ROUND(SUM(em.quantity* em.masterItem.unitPrice), 2) " +
                    "FROM QualityMasterEntity em"
    )
    Double getGrandTotal();
}
