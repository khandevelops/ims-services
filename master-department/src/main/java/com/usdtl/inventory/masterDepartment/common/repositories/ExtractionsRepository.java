package com.usdtl.inventory.masterDepartment.common.repositories;

import com.usdtl.inventory.masterDepartment.common.entities.ExtractionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionsRepository extends JpaRepository<ExtractionsEntity, Integer> {
}
