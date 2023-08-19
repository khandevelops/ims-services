package com.usdtl.inventory.masterDepartment.masterProcessingLab;

import com.usdtl.inventory.masterDepartment.masterQcInternalStandards.QcInternalStandardsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingLabRepository extends PagingAndSortingRepository<ProcessingLabEntity, Integer> {
}
