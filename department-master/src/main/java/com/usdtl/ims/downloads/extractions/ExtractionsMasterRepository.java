package com.usdtl.ims.downloads.extractions;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionsMasterRepository extends PagingAndSortingRepository<ExtractionsMasterEntity, Integer> {
}
