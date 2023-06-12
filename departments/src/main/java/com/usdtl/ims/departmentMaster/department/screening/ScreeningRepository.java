package com.usdtl.ims.departmentMaster.department.screening;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRepository extends PagingAndSortingRepository<ScreeningEntity, Integer> {
}
