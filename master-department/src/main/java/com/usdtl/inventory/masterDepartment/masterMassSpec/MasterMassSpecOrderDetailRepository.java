package com.usdtl.inventory.masterDepartment.masterMassSpec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterMassSpecOrderDetailRepository extends JpaRepository<MasterMassSpecOrderDetailEntity, Integer> {
//    MasterShippingOrderDetailEntity findExtractionsMasterOrderDetailEntityBy (Integer extractionsMasterId);
}
