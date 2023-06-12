package com.usdtl.ims.departmentMaster.quality;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface QualityMasterRepository extends PagingAndSortingRepository<QualityMasterEntity, Integer> {
}
