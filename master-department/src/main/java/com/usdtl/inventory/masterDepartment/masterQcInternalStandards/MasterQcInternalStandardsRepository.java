package com.usdtl.inventory.masterDepartment.masterQcInternalStandards;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterQcInternalStandardsRepository extends PagingAndSortingRepository<MasterQcInternalStandardsEntity, Integer> {
    Page<MasterQcInternalStandardsEntity> findByDepartmentItemsIsNotEmpty(Pageable pageable);
}
