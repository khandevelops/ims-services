package com.usdtl.inventory.masterDepartment.masterExtractions;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterExtractionsRepository extends PagingAndSortingRepository<MasterExtractionsEntity, Integer> {

}
