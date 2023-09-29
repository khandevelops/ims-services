package com.usdtl.inventory.masterDepartment.masterRd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRdOrderDetailRepository extends JpaRepository<MasterRdOrderDetailEntity, Integer> {
//    MasterShippingOrderDetailEntity findExtractionsMasterOrderDetailEntityBy (Integer extractionsMasterId);
}
