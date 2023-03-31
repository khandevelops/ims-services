package com.usdtl.ims.master;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends PagingAndSortingRepository<MasterEntity, Integer> {
//    Page<MasterEntity> findAllByItemContaining(String item, Pageable pageable);
//    Page<MasterEntity> findAllByKeyword(String item, Pageable pageable);

//    @Query(value = "select * from inventory.master where" + "concat()")
    Page<MasterEntity> findAllByItemContainingIgnoreCase(String item, Pageable pageable);
}
