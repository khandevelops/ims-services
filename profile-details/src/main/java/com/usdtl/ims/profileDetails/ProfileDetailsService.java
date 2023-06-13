package com.usdtl.ims.profileDetails;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfileDetailsService {
    private ProfileDetailsRepository repository;

    public List<ProfileDetailsEntity> getProfileDetails() {
        return (List<ProfileDetailsEntity>) repository.findAll();
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

    public List<ProfileDetailsEntity> syncProfileDetails(List<ProfileDetailsEntity> profileDetailsRequest) {
        profileDetailsRequest.forEach(profileDetail -> {
            boolean profileDetailExist = repository.existsById(profileDetail.getId());
            if(profileDetailExist) {
                ProfileDetailsEntity existingProfileDetail = repository.findById(profileDetail.getId()).orElseThrow();
                existingProfileDetail.setId(profileDetail.getId());
                repository.save(existingProfileDetail);
            }
            if(!profileDetailExist) {
                ProfileDetailsEntity newUser = ProfileDetailsEntity.builder()
                        .id(profileDetail.getId())
                        .department(null)
                        .role(null)
                        .permission(null)
                        .build();
                repository.save(newUser);
            }
            if(profileDetailExist) {
                ProfileDetailsEntity profileDetailsEntity = repository.findById(profileDetail.getId()).orElseThrow();
                profileDetailsEntity.setDepartment(profileDetail.getDepartment());
                profileDetailsEntity.setRole(profileDetail.getRole());
                profileDetailsEntity.setPermission(profileDetail.getPermission());
                repository.save(profileDetailsEntity);
            }
        });
        return (List<ProfileDetailsEntity>) repository.findAll();
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