package com.usdtl.inventory.masterDepartment.masterQcQa;

import com.usdtl.inventory.masterDepartment.masterQcInternalStandards.QcInternalStandardsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QcQaRepository extends PagingAndSortingRepository<QcQaEntity, Integer> {
}
