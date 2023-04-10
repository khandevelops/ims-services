package com.usdtl.ims.department.extractions;

import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionsRepository extends PagingAndSortingRepository<ExtractionsEntity, Integer> {
    @Query(value = "SELECT new com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest" +
            "(em.id, m.id, m.item, m.purchase_unit, m.part_number, m.recent_cn, m.recent_vendor, em.location, SUM(em.quantity), em.usage_level, em.min_quantity, em.max_quantity, m.average_unit_price, em.lot_number, m.category, m.comment)" +

            " FROM ExtractionsEntity AS em JOIN MasterEntity AS m ON m.id = em.item_id GROUP BY em.item_id, em.usage_level, em.min_quantity, em.id, em.max_quantity"
    )
    Page<DepartmentMasterResponseTest> getDepartmentTransformedItems(Pageable pageable);
}
