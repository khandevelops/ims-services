package com.usdtl.inventory.masterDepartment.masterScreening;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterScreeningRepository extends PagingAndSortingRepository<MasterScreeningEntity, Integer> {
}
