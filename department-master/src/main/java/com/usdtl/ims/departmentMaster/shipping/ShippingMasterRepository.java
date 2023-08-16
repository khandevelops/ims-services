package com.usdtl.ims.departmentMaster.shipping;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface ShippingMasterRepository extends PagingAndSortingRepository<ShippingMasterEntity, Integer> {
    @Query(
            value = "SELECT ROUND(SUM(em.quantity* em.masterItem.unitPrice), 2) " +
                    "FROM ShippingMasterEntity em"
    )
    Double getGrandTotal();
}
