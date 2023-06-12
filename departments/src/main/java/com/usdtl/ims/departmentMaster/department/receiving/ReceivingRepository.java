package com.usdtl.ims.departmentMaster.department.receiving;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivingRepository extends PagingAndSortingRepository<ReceivingEntity, Integer> {
}
