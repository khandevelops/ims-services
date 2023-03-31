package com.usdtl.inventory.masterDepartment.masterMassSpec;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterMassSpecRepository extends PagingAndSortingRepository<MasterMassSpecEntity, Integer> {
}
