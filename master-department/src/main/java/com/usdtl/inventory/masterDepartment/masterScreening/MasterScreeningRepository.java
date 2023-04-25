package com.usdtl.inventory.masterDepartment.masterScreening;

import com.usdtl.inventory.masterDepartment.masterReceiving.MasterReceivingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterScreeningRepository extends PagingAndSortingRepository<MasterScreeningEntity, Integer> {
    Page<MasterScreeningEntity> findByDepartmentItemsIsNotEmpty(Pageable pageable);
}
