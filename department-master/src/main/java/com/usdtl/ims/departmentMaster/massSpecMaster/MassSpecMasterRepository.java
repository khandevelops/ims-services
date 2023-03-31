package com.usdtl.ims.departmentMaster.massSpecMaster;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MassSpecMasterRepository extends PagingAndSortingRepository<MassSpecMasterEntity, Integer> {
}
