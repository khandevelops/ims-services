package com.usdtl.ims.departmentMaster.processingLabMaster;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface ProcessingLabMasterRepository extends PagingAndSortingRepository<ProcessingLabMasterEntity, Integer> {
    @Query(
            value = "SELECT ROUND(SUM(em.quantity* em.masterItem.unitPrice), 2) " +
                    "FROM ProcessingLabMasterEntity em"
    )
    Double getGrandTotal();
}
