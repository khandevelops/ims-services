package com.usdtl.inventory.masterDepartment.common.repositories;

import com.usdtl.inventory.masterDepartment.common.entities.RdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdRepository extends JpaRepository<RdEntity, Integer> {
}
