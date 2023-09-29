package com.usdtl.inventory.masterDepartment.masterShipping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterShippingOrderDetailRepository extends JpaRepository<MasterShippingOrderDetailEntity, Integer> {
//    MasterShippingOrderDetailEntity findExtractionsMasterOrderDetailEntityBy (Integer extractionsMasterId);
}
