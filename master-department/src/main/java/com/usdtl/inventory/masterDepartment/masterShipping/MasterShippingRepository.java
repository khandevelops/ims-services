package com.usdtl.inventory.masterDepartment.masterShipping;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterShippingRepository extends PagingAndSortingRepository<MasterShippingEntity, Integer> {
    Page<MasterShippingEntity> findByDepartmentItemsIsNotEmpty(Pageable pageable);
}
