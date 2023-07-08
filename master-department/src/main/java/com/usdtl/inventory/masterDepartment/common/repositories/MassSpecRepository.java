package com.usdtl.inventory.masterDepartment.common.repositories;

import com.usdtl.inventory.masterDepartment.common.entities.MassSpecEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MassSpecRepository extends JpaRepository<MassSpecEntity, Integer> {
}
