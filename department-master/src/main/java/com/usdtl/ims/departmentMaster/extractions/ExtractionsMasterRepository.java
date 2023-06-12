package com.usdtl.ims.departmentMaster.extractions;

import com.usdtl.ims.departmentMaster.common.response.DepartmentMasterResponse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionsMasterRepository extends PagingAndSortingRepository<ExtractionsMasterEntity, Integer> {
}
