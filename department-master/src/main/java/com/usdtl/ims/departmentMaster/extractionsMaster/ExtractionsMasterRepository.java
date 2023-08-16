package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.departmentMaster.common.GrandTotal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtractionsMasterRepository extends PagingAndSortingRepository<ExtractionsMasterEntity, Integer> {
    @Query(
            value = "SELECT ROUND(SUM(em.quantity* em.masterItem.unitPrice), 2) " +
            "FROM ExtractionsMasterEntity em"
    )
    Double getGrandTotal();
}
