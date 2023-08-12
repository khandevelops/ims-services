package com.usdtl.ims.profileDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDetailsRepository extends PagingAndSortingRepository<ProfileDetailsEntity, String> {
    Page<ProfileDetailsEntity> findByDisplayNameIsContainingIgnoreCase(String displayName, Pageable pageable);
}
