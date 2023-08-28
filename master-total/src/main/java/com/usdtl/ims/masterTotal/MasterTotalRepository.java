package com.usdtl.ims.masterTotal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterTotalRepository extends JpaRepository<MasterTotalEntity, Integer> {
    @Query(value = "SELECT mt FROM MasterTotalEntity AS mt")
    Page<MasterTotalEntity> findAll(Pageable pageable);
}
