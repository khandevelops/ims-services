package com.usdtl.inventory.masterDepartment.masterExtractions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterExtractionsOrderDetailRepository extends JpaRepository<MasterExtractionsOrderDetailEntity, Integer> {
//    MasterExtractionsOrderDetailEntity findExtractionsMasterOrderDetailEntityBy (Integer extractionsMasterId);
}
