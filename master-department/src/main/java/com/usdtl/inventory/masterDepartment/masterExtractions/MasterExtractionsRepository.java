package com.usdtl.inventory.masterDepartment.masterExtractions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterExtractionsRepository extends PagingAndSortingRepository<MasterExtractionsEntity, Integer> {
    Page<MasterExtractionsEntity> findByDepartmentItemsIsNotEmpty(Pageable pageable);

    @Query(value = "SELECT md FROM MasterExtractionsEntity AS md WHERE md.departmentItems IS NOT EMPTY ORDER BY md.masterTotal.totalQuantity ASC")
    Page<MasterExtractionsEntity> findAllByTotalQuantityOrderByAsc(Pageable pageable);

    @Query(value = "SELECT md FROM MasterExtractionsEntity AS md WHERE md.departmentItems IS NOT EMPTY ORDER BY md.masterTotal.totalPrice ASC")
    Page<MasterExtractionsEntity> findAllByTotalPriceOrderByAsc(Pageable pageable);

    @Query(value = "SELECT md FROM MasterExtractionsEntity AS md WHERE md.departmentItems IS NOT EMPTY ORDER BY md.masterTotal.totalQuantity DESC")
    Page<MasterExtractionsEntity> findAllByTotalQuantityOrderByDesc(Pageable pageable);

    @Query(value = "SELECT md FROM MasterExtractionsEntity AS md WHERE md.departmentItems IS NOT EMPTY ORDER BY md.masterTotal.totalPrice DESC")
    Page<MasterExtractionsEntity> findAllByTotalPriceOrderByDesc(Pageable pageable);

    @Query(value = "SELECT m FROM MasterExtractionsEntity AS m WHERE "
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
            + " OR m.comment LIKE %?1%"
            + " OR cast(m.masterTotal.totalQuantity as string) LIKE %?1%"
            + " OR cast(m.masterTotal.totalPrice as string) LIKE %?1%)"
    )
    Page<MasterExtractionsEntity> findAllByKeyword(String keyword, Pageable pageable);
}
