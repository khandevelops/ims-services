package com.usdtl.inventory.masterDepartment.masterScreening;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterScreeningOrderDetailRepository extends JpaRepository<MasterScreeningOrderDetailEntity, Integer> { }
