package com.usdtl.ims.departmentMaster.shipping;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface ShippingMasterRepository extends PagingAndSortingRepository<ShippingMasterEntity, Integer> {
    @Query(value = "SELECT ROUND(SUM(quantity * unit_price), 2) FROM inventory.shipping JOIN inventory.master on inventory.shipping.item_id = inventory.master.id", nativeQuery = true)
    Double getGrandTotal();
}
