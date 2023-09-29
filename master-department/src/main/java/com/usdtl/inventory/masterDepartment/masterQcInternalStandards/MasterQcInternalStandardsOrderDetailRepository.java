package com.usdtl.inventory.masterDepartment.masterQcInternalStandards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterQcInternalStandardsOrderDetailRepository extends JpaRepository<MasterQcInternalStandardsOrderDetailEntity, Integer> {
//    MasterShippingOrderDetailEntity findExtractionsMasterOrderDetailEntityBy (Integer extractionsMasterId);
}
