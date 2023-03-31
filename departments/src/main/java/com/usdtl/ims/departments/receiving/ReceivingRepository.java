package com.usdtl.ims.departments.receiving;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivingRepository extends PagingAndSortingRepository<ReceivingEntity, Integer> {
}
