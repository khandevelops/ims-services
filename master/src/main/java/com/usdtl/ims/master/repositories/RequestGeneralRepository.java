package com.usdtl.ims.master.repositories;

import com.usdtl.ims.master.entities.RequestGeneralEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestGeneralRepository extends JpaRepository<RequestGeneralEntity, Integer> {
}
