package com.usdtl.inventory.masterDepartment.masterReceiving;

import com.usdtl.inventory.masterDepartment.masterRd.MasterRdEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterReceivingRepository extends PagingAndSortingRepository<MasterReceivingEntity, Integer> {
    Page<MasterReceivingEntity> findByDepartmentItemsIsNotEmpty(Pageable pageable);
}
