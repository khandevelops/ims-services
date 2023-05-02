package com.usdtl.ims.master;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDetailsRepository extends PagingAndSortingRepository<ProfileDetailsEntity, String> {
}
