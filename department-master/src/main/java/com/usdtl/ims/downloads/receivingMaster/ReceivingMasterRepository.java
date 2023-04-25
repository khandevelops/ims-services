package com.usdtl.ims.downloads.receivingMaster;

import com.usdtl.ims.clients.DepartmentMasterResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivingMasterRepository extends PagingAndSortingRepository<ReceivingMasterEntity, Integer> {
    @Query(value = "SELECT m.id AS master_item_id, " +
            "m.item, " +
            "m.purchase_unit, " +
            "m.part_number,  " +
            "m.recent_cn, " +
            "e.id AS department_item_id, " +
            "e.usage_level, " +
            "e.min_quantity, " +
            "e.max_quantity, " +
            "e.received_date, " +
            "e.expiration_date, " +
            "e.lot_number, " +
            "m.average_unit_price, " +
            "m.comment, " +
            "m.category, " +
            "SUM(quantity) * m.average_unit_price AS total_price, " +
            "SUM(quantity) AS total_quantity," +
            "IF(e.min_quantity IS NULL OR e.max_quantity IS NULL, NULL, IF(e.min_quantity = 1 AND e.max_quantity = 1, IF(SUM(e.quantity) < 1, 1, 0), IF(SUM(e.quantity) <= e.min_quantity, e.max_quantity - e.min_quantity, 0))) AS order_quantity " +
            "FROM MasterEntity AS m " +
            "JOIN ExtractionsMasterEntity AS e " +
            "ON m.id = e.item_id " +
            "GROUP BY e.item_id, " +
            "e.usage_level, " +
            "e.min_quantity, " +
            "e.id, " +
            "e.max_quantity", nativeQuery = true)
    Page<DepartmentMasterResponse> getDepartmentMasterItems(Pageable pageable);
}
