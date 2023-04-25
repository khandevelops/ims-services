package com.usdtl.inventory.masterDepartment.masterMassSpec;

import com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterMassSpecRepository extends PagingAndSortingRepository<MasterMassSpecEntity, Integer> {
    Page<MasterMassSpecEntity> findByDepartmentItemsIsNotEmpty(Pageable pageable);
}
