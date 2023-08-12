package com.usdtl.ims.profileDetails;

import com.usdtl.ims.common.exceptions.constants.Permission;
import com.usdtl.ims.common.exceptions.constants.Role;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfileDetailsService {
    private ProfileDetailsRepository repository;

    public Page<ProfileDetailsEntity> getProfileDetails(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 50, Sort.by("displayName").ascending());
         return repository.findAll(pageRequest);
    }

    public Page<ProfileDetailsEntity> filterProfileDetails(String displayName, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 50, Sort.by("displayName").ascending());
        return repository.findByDisplayNameIsContainingIgnoreCase(displayName, pageRequest);
    }

    public ProfileDetailsEntity getProfileDetail(String id) {
        return repository.findById(id).orElseThrow();
    }

    public List<ProfileDetailsEntity> createProfileDetail(List<ProfileDetailsEntity> profileDetailRequest) {
        profileDetailRequest.forEach(profileDetail -> {
            boolean userExists = repository.existsById(profileDetail.getId());
            if(!userExists) {
                ProfileDetailsEntity newUser = ProfileDetailsEntity.builder()
                        .id(profileDetail.getId())
                        .department(null)
                        .role(null)
                        .build();
                repository.save(newUser);
            }
        });
        return (List<ProfileDetailsEntity>) repository.findAll();
    }

    public Page<ProfileDetailsEntity> syncProfileDetails(List<ProfileDetailsEntity> profileDetailsRequest, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 50, Sort.by("displayName").ascending());
        profileDetailsRequest.forEach(profileDetail -> {
            boolean exits = repository.existsById(profileDetail.getId());
            if(!exits) {
                ProfileDetailsEntity newUser = ProfileDetailsEntity.builder()
                        .id(profileDetail.getId())
                        .displayName(profileDetail.getDisplayName())
                        .userPrincipalName(profileDetail.getUserPrincipalName())
                        .department(null)
                        .role(Role.USER)
                        .permission(Permission.ALLOW)
                        .build();
                repository.save(newUser);
            } else {
                ProfileDetailsEntity profileDetailsEntity = repository.findById(profileDetail.getId()).orElseThrow();
                if(profileDetail.getDisplayName() != null) {
                    profileDetailsEntity.setDisplayName(profileDetail.getDisplayName());
                }
                if(profileDetail.getUserPrincipalName() != null) {
                    profileDetailsEntity.setUserPrincipalName(profileDetail.getUserPrincipalName());
                }
                if(profileDetail.getDepartment() != null) {
                    profileDetailsEntity.setDepartment(profileDetail.getDepartment());
                }
                if(profileDetail.getRole() != null) {
                    profileDetailsEntity.setRole(profileDetail.getRole());
                } else {
                    profileDetailsEntity.setRole(Role.USER);
                }
                if(profileDetail.getPermission() != null) {
                    profileDetailsEntity.setPermission(profileDetail.getPermission());
                } else {
                    profileDetailsEntity.setPermission(Permission.ALLOW);
                }
                repository.save(profileDetailsEntity);
            }
        });
        return repository.findAll(pageRequest);
    }

    public ProfileDetailsEntity updateProfileDetail(String id, ProfileDetailsEntity profileDetailRequest) {
        ProfileDetailsEntity profileDetails = repository.findById(id).orElseThrow();
        profileDetails.setDepartment(profileDetailRequest.getDepartment());
        profileDetails.setRole(profileDetailRequest.getRole());
        profileDetails.setPermission(profileDetailRequest.getPermission());
        repository.save(profileDetails);

        return profileDetails;
    }
}
