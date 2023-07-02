package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.departmentMaster.common.GrandTotal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtractionsMasterRepository extends PagingAndSortingRepository<ExtractionsMasterEntity, Integer> {
//    @Query(value = "SELECT ROUND(SUM(e.quantity * m.unit_price), 2) FROM ExtractionsMasterEntity e LEFT JOIN MasterEntity m on ExtractionsMasterEntity.masterItem.id = MasterEntity.id")
    @Query(value = "SELECT ROUND(SUM(quantity * unit_price), 2) FROM inventory.extractions JOIN inventory.master on inventory.extractions.item_id = inventory.master.id", nativeQuery = true)
    Double getGrandTotal();
}
