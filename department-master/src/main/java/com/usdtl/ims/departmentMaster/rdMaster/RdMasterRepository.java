package com.usdtl.ims.departmentMaster.rdMaster;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdMasterRepository extends PagingAndSortingRepository<RdMasterEntity, Integer> {
}
