package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.clients.responseClass.DepartmentDownloadResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtractionsRepository extends PagingAndSortingRepository<ExtractionsEntity, Integer> {
    @Query(value = "SELECT new com.usdtl.ims.clients.responseClass.DepartmentDownloadResponse" +
            "(m.item, m.purchase_unit, m.part_number, m.recent_cn, m.recent_vendor, m.average_unit_price, m.category, m.comment, e.item_id, e.id, e.usage_level, e.min_quantity, e.max_quantity, e.lot_number, e.location, e.received_date, e.expiration_date, e.quantity)" +

            " FROM RdEntity AS e LEFT JOIN MasterEntity AS m ON m.id = e.item_id GROUP BY e.item_id, e.id, e.usage_level, e.min_quantity, e.max_quantity, e.lot_number, e.location, e.received_date, e.expiration_date, e.quantity"
    )
    List<DepartmentDownloadResponse> getDepartmentTransformedItems();
}
