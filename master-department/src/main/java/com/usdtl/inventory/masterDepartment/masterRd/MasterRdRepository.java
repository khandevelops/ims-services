package com.usdtl.inventory.masterDepartment.masterRd;

import com.usdtl.inventory.masterDepartment.masterQuality.MasterQualityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRdRepository extends PagingAndSortingRepository<MasterRdEntity, Integer> {
    Page<MasterRdEntity> findByDepartmentItemsIsNotEmpty(Pageable pageable);
}
