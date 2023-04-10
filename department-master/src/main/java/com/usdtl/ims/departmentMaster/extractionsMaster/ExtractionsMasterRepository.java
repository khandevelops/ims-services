package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionsMasterRepository extends PagingAndSortingRepository<ExtractionsMasterEntity, Integer> {

    @Query(value = "SELECT new com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest" +
            "(em.id, m.id, m.item, m.purchase_unit, m.part_number, m.recent_cn, m.recent_vendor, em.location, SUM(em.quantity), em.usage_level, em.min_quantity, em.max_quantity, m.average_unit_price, em.lot_number, m.category, m.comment)" +

            " FROM ExtractionsMasterEntity AS em JOIN MasterEntity AS m ON em.masterItem.id = m.id GROUP BY em.masterItem.id, em.usage_level, em.min_quantity, em.id, em.max_quantity"
    )
    Page<DepartmentMasterResponseTest> getDepartmentMasterItems(Pageable pageable);
}
