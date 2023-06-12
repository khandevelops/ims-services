package com.usdtl.ims.departmentMaster.department.massSpec;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MassSpecRepository extends PagingAndSortingRepository<MassSpecEntity, Integer> {
}
