package com.usdtl.ims.departmentMaster.qcInternalStandardsMaster;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface QcInternalStandardsRepository extends PagingAndSortingRepository<QcInternalStandardsEntity, Integer> {
    @Query(
            value = "SELECT ROUND(SUM(em.quantity* em.masterItem.unitPrice), 2) " +
                    "FROM QcInternalStandardsEntity em"
    )
    Double getGrandTotal();
}
