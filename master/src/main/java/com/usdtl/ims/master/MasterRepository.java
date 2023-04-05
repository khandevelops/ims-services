package com.usdtl.ims.master;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends PagingAndSortingRepository<MasterEntity, Integer> {
    Page<MasterEntity> findAllByItemContainingIgnoreCase(String item, Pageable pageable);

    @Query(value = "SELECT i FROM MasterEntity i WHERE i.item LIKE %?1%"
            + " OR i.manufacturer LIKE %?1%"
            + " OR i.recent_cn LIKE %?1%"
            + " OR i.part_number LIKE %?1%"
            + " OR i.recent_vendor LIKE %?1%"
            + " OR i.fisher_cn LIKE %?1%"
            + " OR i.vwr_cn LIKE %?1%"
            + " OR i.lab_source_cn LIKE %?1%"
            + " OR i.next_advance_cn LIKE %?1%"
            + " OR i.purchase_unit LIKE %?1%"
            + " OR i.comment LIKE %?1%"
            + " OR i.category LIKE %?1%"
            + " OR i.type LIKE %?1%"
            + " OR i.group LIKE %?1%")
    Page<MasterEntity> findAllByKeyword(String keyword, Pageable pageable);
}
