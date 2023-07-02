package com.usdtl.ims.master.repositories;

import com.usdtl.ims.master.entities.RequestOfficeSupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestOfficeSupplyRepository extends JpaRepository<RequestOfficeSupplyEntity, Integer> {
}
