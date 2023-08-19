package com.usdtl.ims.departments.processingLab;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingLabRepository extends PagingAndSortingRepository<ProcessingLabEntity, Integer> {
}
