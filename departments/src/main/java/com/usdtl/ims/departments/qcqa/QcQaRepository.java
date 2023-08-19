package com.usdtl.ims.departments.qcqa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QcQaRepository extends PagingAndSortingRepository<QcQaEntity, Integer> {
}
