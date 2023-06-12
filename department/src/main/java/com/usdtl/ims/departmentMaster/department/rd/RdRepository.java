package com.usdtl.ims.departmentMaster.department.rd;

import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdRepository extends PagingAndSortingRepository<RdEntity, Integer> {
    @Query(value = "SELECT new com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest" +
            "(m.item, m.purchase_unit, m.part_number, m.recent_cn, m.recent_vendor, m.average_unit_price, m.category, m.comment, e.item_id, e.usage_level, e.min_quantity, e.max_quantity, SUM(e.quantity))" +

            " FROM RdEntity AS e INNER JOIN MasterEntity AS m ON m.id = e.item_id GROUP BY e.item_id, e.usage_level, e.min_quantity, e.max_quantity"
    )
    Page<DepartmentMasterResponseTest> getDepartmentTransformedItems(Pageable pageable);

    @Query(value = "SELECT new com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest" +
            "(m.item, m.purchase_unit, m.part_number, m.recent_cn, m.recent_vendor, m.average_unit_price, m.category, m.comment, e.item_id, e.id, e.usage_level, e.min_quantity, e.max_quantity, e.location, e.lot_number, e.received_date, e.expiration_date, e.quantity, SUM(e.quantity))" +

            " FROM RdEntity AS e RIGHT JOIN MasterEntity AS m ON m.id = e.item_id"
    )
    Page<DepartmentMasterResponseTest> getDepartmentMasterItems(Pageable pageable);
}
