package com.usdtl.inventory.masterDepartment.masterQuality;

import com.usdtl.inventory.masterDepartment.masterMassSpec.MasterMassSpecEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterQualityRepository extends PagingAndSortingRepository<MasterQualityEntity, Integer> {
    Page<MasterQualityEntity> findByDepartmentItemsIsNotEmpty(Pageable pageable);
}
