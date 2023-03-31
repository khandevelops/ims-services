package com.usdtl.ims.departments.extractions;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionsRepository extends PagingAndSortingRepository<ExtractionsEntity, Integer> {
}
