package com.usdtl.ims.departmentMaster.qualityMaster;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityMasterRepository extends PagingAndSortingRepository<QualityMasterEntity, Integer> {
}
