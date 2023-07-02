package com.usdtl.ims.master.repositories;

import com.usdtl.ims.master.entities.RequestStoreRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestStoreRoomRepository extends JpaRepository<RequestStoreRoomEntity, Integer> {
}
