package com.usdtl.ims.requestDepartment.requestStoreRoom;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestStoreRoomRepository extends PagingAndSortingRepository<RequestStoreRoomEntity, Integer> {
}
