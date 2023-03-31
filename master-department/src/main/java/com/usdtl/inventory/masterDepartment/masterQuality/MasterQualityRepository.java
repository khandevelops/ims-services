package com.usdtl.inventory.masterDepartment.masterQuality;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterQualityRepository extends PagingAndSortingRepository<MasterQualityEntity, Integer> {
}
