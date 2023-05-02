package com.usdtl.ims.master;

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

    public List<ProfileDetailsEntity> createUsers(List<ProfileDetailsEntity> usersRequest) {
        usersRequest.forEach(userRequest -> {
            boolean userExists = repository.existsById(userRequest.getId());
            if(!userExists) {
                ProfileDetailsEntity newUser = ProfileDetailsEntity.builder()
                        .id(userRequest.getId())
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
        });
        return (List<ProfileDetailsEntity>) repository.findAll();
    }

    public ProfileDetailsEntity updateUser(String id, ProfileDetailsEntity profileDetailRequest) {
        ProfileDetailsEntity profileDetails = repository.findById(id).orElseThrow();
        profileDetails.setDepartment(profileDetailRequest.getDepartment());
        profileDetails.setRole(profileDetailRequest.getRole());
        profileDetails.setPermission(profileDetailRequest.getPermission());
        repository.save(profileDetails);

        return profileDetails;
    }
}
