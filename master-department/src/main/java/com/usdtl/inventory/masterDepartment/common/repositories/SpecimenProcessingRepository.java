package com.usdtl.inventory.masterDepartment.common.repositories;

import com.usdtl.inventory.masterDepartment.common.entities.SpecimenProcessingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecimenProcessingRepository extends JpaRepository<SpecimenProcessingEntity, Integer> {
}
