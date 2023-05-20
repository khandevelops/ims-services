package com.usdtl.ims.master.repositories;

import com.usdtl.ims.master.entities.QualityEntity;
import com.usdtl.ims.master.entities.ShippingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityRepository extends JpaRepository<QualityEntity, Integer> {
}
