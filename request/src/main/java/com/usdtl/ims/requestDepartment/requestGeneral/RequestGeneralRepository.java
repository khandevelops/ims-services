package com.usdtl.ims.requestDepartment.requestGeneral;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestGeneralRepository extends PagingAndSortingRepository<RequestGeneralEntity, Integer> {
}
