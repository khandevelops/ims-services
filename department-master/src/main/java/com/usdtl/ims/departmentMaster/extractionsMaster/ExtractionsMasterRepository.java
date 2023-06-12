package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionsMasterRepository extends PagingAndSortingRepository<DepartmentMasterResponse, Integer> {
}
