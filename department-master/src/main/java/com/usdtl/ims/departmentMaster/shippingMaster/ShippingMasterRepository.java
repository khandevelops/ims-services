package com.usdtl.ims.departmentMaster.shippingMaster;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingMasterRepository extends PagingAndSortingRepository<ShippingMasterEntity, Integer> {
}
