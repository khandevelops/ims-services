package com.usdtl.ims.departmentMaster.department.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<MasterEntity, Integer> {
}
