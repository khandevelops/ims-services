package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.clients.DepartmentMasterResponse;
import com.usdtl.ims.departmentMaster.master.MasterEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionsMasterRepository extends PagingAndSortingRepository<ExtractionsMasterEntity, Integer> {

    @Query(value = "SELECT id " +
            "FROM inventory.extractions", nativeQuery = true)
    Page<ExtractionsMasterEntity> getDepartmentMasterItems(Pageable pageable);
//    @Query(value = "SELECT m.id AS master_item_id, " +
//            "m.item as item, " +
//            "m.purchase_unit, " +
//            "m.part_number,  " +
//            "m.recent_cn, " +
//            "e.id AS department_item_id, " +
//            "e.usage_level, " +
//            "e.min_quantity, " +
//            "e.max_quantity, " +
//            "e.received_date, " +
//            "e.expiration_date, " +
//            "e.lot_number, " +
//            "m.average_unit_price, " +
//            "m.comment, " +
//            "m.category, " +
//            "SUM(e.quantity) AS total_quantity " +
//            "FROM ExtractionsMasterEntity AS e " +
//            "JOIN MasterEntity AS m " +
//            "ON m.id = e.masterItem.id " +
//            "GROUP BY e.masterItem.id, " +
//            "e.usage_level, " +
//            "e.min_quantity, " +
//            "e.id, " +
//            "e.max_quantity")
//    Page<ExtractionsMasterEntity> getDepartmentMasterItems(Pageable pageable);
}
