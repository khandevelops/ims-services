package com.usdtl.ims.departmentMaster.shipping;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface ShippingMasterRepository extends PagingAndSortingRepository<ShippingMasterEntity, Integer> {
}
