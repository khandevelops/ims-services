package com.usdtl.inventory.masterDepartment.masterRd;

import com.usdtl.inventory.masterDepartment.masterExtractions.ExtractionsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdRepository extends PagingAndSortingRepository<RdEntity, Integer> {
}
