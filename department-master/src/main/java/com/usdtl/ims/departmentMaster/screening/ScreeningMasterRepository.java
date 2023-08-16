package com.usdtl.ims.departmentMaster.screening;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningMasterRepository extends PagingAndSortingRepository<ScreeningMasterEntity, Integer> {
    @Query(
            value = "SELECT ROUND(SUM(em.quantity* em.masterItem.unitPrice), 2) " +
                    "FROM ScreeningMasterEntity em"
    )
    Double getGrandTotal();
}
