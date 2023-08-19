package com.usdtl.ims.departmentMaster.qcqa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface QcQaMasterRepository extends PagingAndSortingRepository<QcQaMasterEntity, Integer> {
    @Query(
            value = "SELECT ROUND(SUM(em.quantity* em.masterItem.unitPrice), 2) " +
                    "FROM QcQaMasterEntity em"
    )
    Double getGrandTotal();
}
