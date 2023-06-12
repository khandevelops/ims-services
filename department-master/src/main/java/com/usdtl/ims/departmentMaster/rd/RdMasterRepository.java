package com.usdtl.ims.departmentMaster.rd;

import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface RdMasterRepository extends PagingAndSortingRepository<RdMasterEntity, Integer> {
}
