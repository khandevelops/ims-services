package com.usdtl.ims.departmentMaster.massSpec;

import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface MassSpecMasterRepository extends PagingAndSortingRepository<MassSpecMasterEntity, Integer> {
}
