package com.usdtl.inventory.masterDepartment.masterQcQa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterQcQaOrderDetailRepository extends JpaRepository<MasterQcQaOrderDetailEntity, Integer> {
//    MasterShippingOrderDetailEntity findExtractionsMasterOrderDetailEntityBy (Integer extractionsMasterId);
}
