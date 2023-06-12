package com.usdtl.ims.departmentMaster.department.rd;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdRepository extends PagingAndSortingRepository<RdEntity, Integer> {
}
