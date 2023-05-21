package com.usdtl.ims.master.repositories;

import com.usdtl.ims.master.entities.RdEntity;
import com.usdtl.ims.master.entities.ReceivingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdRepository extends JpaRepository<RdEntity, Integer> {
}
