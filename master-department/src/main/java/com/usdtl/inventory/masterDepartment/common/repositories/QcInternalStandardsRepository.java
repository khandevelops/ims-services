package com.usdtl.inventory.masterDepartment.common.repositories;

import com.usdtl.inventory.masterDepartment.common.entities.QcInternalStandardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QcInternalStandardsRepository extends JpaRepository<QcInternalStandardsEntity, Integer> {
}
