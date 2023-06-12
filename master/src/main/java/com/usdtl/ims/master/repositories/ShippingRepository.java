package com.usdtl.ims.master.repositories;

import com.usdtl.ims.master.entities.ShippingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<ShippingEntity, Integer> {
}
