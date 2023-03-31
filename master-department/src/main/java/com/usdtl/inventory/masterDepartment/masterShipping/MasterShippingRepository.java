package com.usdtl.inventory.masterDepartment.masterShipping;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterShippingRepository extends PagingAndSortingRepository<MasterShippingEntity, Integer> {
}
