package com.usdtl.ims.master.repositories;

import com.usdtl.ims.master.entities.ScreeningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRepository extends JpaRepository<ScreeningEntity, Integer> {
}
