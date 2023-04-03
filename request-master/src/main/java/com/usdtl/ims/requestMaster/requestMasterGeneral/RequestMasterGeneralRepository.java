package com.usdtl.ims.requestMaster.requestMasterGeneral;

import com.usdtl.ims.common.constants.Confirmation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestMasterGeneralRepository extends PagingAndSortingRepository<RequestMasterGeneralEntity, Integer> {
    Page<RequestMasterGeneralEntity> findByConfirmation(Confirmation confirmation, Pageable pageable);
}
