package com.usdtl.ims.departmentMaster.storeRoom;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import com.usdtl.ims.departmentMaster.specimenProcessing.SpecimenProcessingMasterEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface StoreRoomMasterRepository extends PagingAndSortingRepository<StoreRoomMasterEntity, Integer> {
    @Query(
            value = "SELECT ROUND(SUM(em.quantity* em.masterItem.unitPrice), 2) " +
                    "FROM StoreRoomMasterEntity em"
    )
    Double getGrandTotal();
}
