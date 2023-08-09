package com.usdtl.inventory.masterDepartment.masterSpecimenProcessing;

import com.usdtl.inventory.masterDepartment.masterQcInternalStandards.QcInternalStandardsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecimenProcessingRepository extends PagingAndSortingRepository<QcInternalStandardsEntity, Integer> {
}
