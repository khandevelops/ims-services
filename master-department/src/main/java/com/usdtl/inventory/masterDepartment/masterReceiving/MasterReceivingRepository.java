package com.usdtl.inventory.masterDepartment.masterReceiving;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterReceivingRepository extends PagingAndSortingRepository<MasterReceivingEntity, Integer> {
}
