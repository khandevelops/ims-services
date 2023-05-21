package com.usdtl.ims.master.repositories;

import com.usdtl.ims.master.entities.ExtractionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionsRepository extends JpaRepository<ExtractionsEntity, Integer> {
}
