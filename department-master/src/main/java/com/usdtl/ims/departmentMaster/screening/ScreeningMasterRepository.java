package com.usdtl.ims.departmentMaster.screening;

import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface ScreeningMasterRepository extends PagingAndSortingRepository<ScreeningMasterEntity, Integer> {
}
