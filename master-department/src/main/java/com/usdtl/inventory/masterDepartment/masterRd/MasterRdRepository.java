package com.usdtl.inventory.masterDepartment.masterRd;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRdRepository extends PagingAndSortingRepository<MasterRdEntity, Integer> {
}
