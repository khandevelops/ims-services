package com.usdtl.inventory.masterDepartment.masterQcQa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterQcQaRepository extends PagingAndSortingRepository<MasterQcQaEntity, Integer> {
    Page<MasterQcQaEntity> findByDepartmentItemsIsNotEmpty(Pageable pageable);
    List<MasterQcQaEntity> findByDepartmentItemsIsNotEmpty();
    @Query(value = "SELECT m FROM MasterQcQaEntity AS m WHERE "
            + "m.departmentItems IS NOT EMPTY"
            + " AND (m.item LIKE %?1%"
            + " OR m.purchaseUnit LIKE %?1%"
            + " OR m.manufacturer LIKE %?1%"
            + " OR m.recentCN LIKE %?1%"
            + " OR m.partNumber LIKE %?1%"
            + " OR m.recentVendor LIKE %?1%"
            + " OR m.fisherCN LIKE %?1%"
            + " OR m.vwrCN LIKE %?1%"
            + " OR m.labSourceCN LIKE %?1%"
            + " OR m.otherCN LIKE %?1%"
            + " OR cast(m.unitPrice as string) LIKE %?1%"
            + " OR m.category LIKE %?1%"
            + " OR m.drugClass LIKE %?1%"
            + " OR m.itemType LIKE %?1%"
            + " OR m.itemGroup LIKE %?1%"
            + " OR m.comment LIKE %?1%)"
    )
    Page<MasterQcQaEntity> findAllByKeyword(String keyword, Pageable pageable);
}
