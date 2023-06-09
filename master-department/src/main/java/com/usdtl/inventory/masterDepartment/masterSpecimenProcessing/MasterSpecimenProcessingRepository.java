package com.usdtl.inventory.masterDepartment.masterSpecimenProcessing;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterSpecimenProcessingRepository extends PagingAndSortingRepository<MasterSpecimenProcessingEntity, Integer> {
    Page<MasterSpecimenProcessingEntity> findByDepartmentItemsIsNotEmpty(Pageable pageable);
}
