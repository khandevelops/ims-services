package com.usdtl.ims.master.repositories;

import com.usdtl.ims.master.entities.MassSpecEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MassSpecRepository extends JpaRepository<MassSpecEntity, Integer> {
}
