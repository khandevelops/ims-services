package com.usdtl.ims.requestMaster.requestMasterStoreRoom;

import com.usdtl.ims.common.exceptions.constants.Confirmation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestMasterStoreRoomRepository extends PagingAndSortingRepository<RequestMasterStoreRoomEntity, Integer> {
    Page<RequestMasterStoreRoomEntity> findByConfirmation(Confirmation confirmation, Pageable pageable);
}
