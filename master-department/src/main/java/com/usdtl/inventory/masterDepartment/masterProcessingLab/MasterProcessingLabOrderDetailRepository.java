package com.usdtl.inventory.masterDepartment.masterProcessingLab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterProcessingLabOrderDetailRepository extends JpaRepository<MasterProcessingLabOrderDetailEntity, Integer> { }
