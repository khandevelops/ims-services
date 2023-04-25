package com.usdtl.inventory.masterDepartment.masterExtractions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterExtractionsRepository extends PagingAndSortingRepository<MasterExtractionsEntity, Integer> {
    Page<MasterExtractionsEntity> findByDepartmentItemsIsNotEmpty(Pageable pageable);
}
