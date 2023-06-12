package com.usdtl.ims.departmentMaster.requestDepartment.requestOfficeSupply;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestOfficeSupplyRepository extends PagingAndSortingRepository<RequestOfficeSupplyEntity, Integer> {
}
