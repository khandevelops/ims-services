package com.usdtl.ims.master;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            if(!profileDetailExist) {
                ProfileDetailsEntity newUser = ProfileDetailsEntity.builder()
                        .id(profileDetail.getId())
                        .department(null)
                        .role(null)
                        .permission(null)
                        .build();
                repository.save(newUser);
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
