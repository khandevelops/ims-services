package com.usdtl.inventory.masterDepartment.masterQuality;

import com.usdtl.inventory.masterDepartment.masterQcInternalStandards.QcInternalStandardsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityRepository extends PagingAndSortingRepository<QcInternalStandardsEntity, Integer> {
}
