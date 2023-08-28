package com.usdtl.inventory.masterDepartment.masterTotal;

import com.usdtl.inventory.masterDepartment.masterExtractions.MasterTotalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterTotalRepository extends JpaRepository<MasterTotalEntity, Integer> {

}
