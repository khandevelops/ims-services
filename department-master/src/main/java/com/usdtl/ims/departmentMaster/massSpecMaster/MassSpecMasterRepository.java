package com.usdtl.ims.departmentMaster.massSpecMaster;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface MassSpecMasterRepository extends PagingAndSortingRepository<MassSpecMasterEntity, Integer> {
    @Query(
            value = "SELECT ROUND(SUM(em.quantity* em.masterItem.unitPrice), 2) " +
                    "FROM MassSpecMasterEntity em"
    )
    Double getGrandTotal();
}
