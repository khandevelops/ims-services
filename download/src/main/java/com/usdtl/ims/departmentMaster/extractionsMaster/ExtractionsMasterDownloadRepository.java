package com.usdtl.ims.departmentMaster.extractionsMaster;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionsMasterDownloadRepository extends PagingAndSortingRepository<ExtractionsMasterDownloadEntity, Integer> {
}
