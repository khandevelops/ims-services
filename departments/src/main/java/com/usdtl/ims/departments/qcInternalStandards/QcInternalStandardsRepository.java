package com.usdtl.ims.departments.qcInternalStandards;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QcInternalStandardsRepository extends PagingAndSortingRepository<QcInternalStandardsEntity, Integer> {
}
