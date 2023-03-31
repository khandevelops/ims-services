package com.usdtl.ims.departmentMaster.receivingMaster;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivingMasterRepository extends PagingAndSortingRepository<ReceivingMasterEntity, Integer> {
}
