package com.usdtl.ims.master.repositories;

import com.usdtl.ims.master.entities.QcInternalStandardsEntity;
import com.usdtl.ims.master.entities.SpecimenProcessingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QcInternalStandardsRepository extends JpaRepository<QcInternalStandardsEntity, Integer> {
}
