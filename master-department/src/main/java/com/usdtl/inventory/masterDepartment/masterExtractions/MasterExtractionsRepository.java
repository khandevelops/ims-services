package com.usdtl.inventory.masterDepartment.masterExtractions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterExtractionsRepository extends PagingAndSortingRepository<MasterExtractionsEntity, Integer> {
    Page<MasterExtractionsEntity> findByDepartmentItemsIsNotEmpty(Pageable pageable);
    @Query(value = "SELECT m FROM MasterEntity AS m WHERE "
            + "m.item LIKE %?1%"
            + " OR m.purchase_unit LIKE %?1%"
            + " OR m.manufacturer LIKE %?1%"
            + " OR m.recent_cn LIKE %?1%"
            + " OR m.part_number LIKE %?1%"
            + " OR m.recent_vendor LIKE %?1%"
            + " OR m.fisher_cn LIKE %?1%"
            + " OR m.vwr_cn LIKE %?1%"
            + " OR m.lab_source_cn LIKE %?1%"
            + " OR m.other_cn LIKE %?1%"
            + " OR cast(m.unit_price as string) LIKE %?1%"
            + " OR m.category LIKE %?1%"
            + " OR m.drug_class LIKE %?1%"
            + " OR m.type LIKE %?1%"
            + " OR m.group LIKE %?1%"
            + " OR m.comment LIKE %?1%"
    )
    Page<MasterExtractionsEntity> findAllByItemContainingIgnoreCaseAndPurchase_unitContainingIgnoreCase(String keyword, Pageable pageable);
}
