package com.usdtl.ims.requestDepartment.requestGeneral;

import com.usdtl.ims.common.constants.Confirmation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestGeneralRepository extends PagingAndSortingRepository<RequestGeneralEntity, Integer> {
}
