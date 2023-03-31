package com.usdtl.ims.requestMaster.requestMasterOfficeSupply;

import com.usdtl.ims.common.constants.Confirmation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestMasterOfficeSupplyRepository extends PagingAndSortingRepository<RequestMasterOfficeSupplyEntity, Integer> {
    Page<RequestMasterOfficeSupplyEntity> findByConfirmation(Confirmation confirmation, Pageable pageable);
}
