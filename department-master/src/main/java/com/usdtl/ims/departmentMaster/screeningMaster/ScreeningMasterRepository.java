package com.usdtl.ims.departmentMaster.screeningMaster;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningMasterRepository extends PagingAndSortingRepository<ScreeningMasterEntity, Integer> {
}
