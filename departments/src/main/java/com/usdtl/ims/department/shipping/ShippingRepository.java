package com.usdtl.ims.department.shipping;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends PagingAndSortingRepository<ShippingEntity, Integer> {
}
