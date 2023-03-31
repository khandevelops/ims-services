package com.usdtl.ims.requestDepartment.requestStoreRoom;

import com.usdtl.ims.common.constants.Confirmation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestStoreRoomRepository extends PagingAndSortingRepository<RequestStoreRoomEntity, Integer> {
    Page<RequestStoreRoomEntity> findByConfirmation(Confirmation confirmation, Pageable pageable);
}
