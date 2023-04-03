package com.usdtl.ims.requestDepartment.requestOfficeSupply;

import com.usdtl.ims.common.constants.Confirmation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestOfficeSupplyRepository extends PagingAndSortingRepository<RequestOfficeSupplyEntity, Integer> {
}
