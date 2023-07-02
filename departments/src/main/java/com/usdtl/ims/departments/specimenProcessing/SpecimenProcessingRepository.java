package com.usdtl.ims.departments.specimenProcessing;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecimenProcessingRepository extends PagingAndSortingRepository<SpecimenProcessingEntity, Integer> {
}
