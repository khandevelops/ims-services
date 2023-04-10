package com.usdtl.ims.department.quality;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityRepository extends PagingAndSortingRepository<QualityEntity, Integer> {
}
