package com.usdtl.ims.departmentMaster.requestMaster.requestMasterGeneral;

import com.usdtl.ims.common.exceptions.constants.Confirmation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestMasterGeneralRepository extends PagingAndSortingRepository<RequestMasterGeneralEntity, Integer> {
    Page<RequestMasterGeneralEntity> findByConfirmation(Confirmation confirmation, Pageable pageable);
}
