package com.usdtl.ims.departmentMaster.qcInternalStandards;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface QcInternalStandardsRepository extends PagingAndSortingRepository<QcInternalStandardsEntity, Integer> {
}
