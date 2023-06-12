package com.usdtl.ims.departmentMaster.specimenProcessing;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface SpecimenProcessingMasterRepository extends PagingAndSortingRepository<SpecimenProcessingMasterEntity, Integer> {
}
