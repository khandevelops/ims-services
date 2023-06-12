package com.usdtl.ims.departmentMaster.department.extractions;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionsRepository extends PagingAndSortingRepository<ExtractionsEntity, Integer> {
}
