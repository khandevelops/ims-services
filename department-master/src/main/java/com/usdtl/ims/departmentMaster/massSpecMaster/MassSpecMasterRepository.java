package com.usdtl.ims.departmentMaster.massSpecMaster;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface MassSpecMasterRepository extends PagingAndSortingRepository<MassSpecMasterEntity, Integer> {
    @Query(value = "SELECT ROUND(SUM(quantity * unit_price), 2) FROM inventory.mass_spec JOIN inventory.master on inventory.mass_spec.item_id = inventory.master.id", nativeQuery = true)
    Double getGrandTotal();
}
