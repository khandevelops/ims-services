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

    public ProfileDetailsEntity updateUser(String id, ProfileDetailsEntity userRequest) {
        ProfileDetailsEntity profileDetails = repository.findById(id).orElseThrow();
        profileDetails.setDepartment(userRequest.getDepartment() == null ? profileDetails.getDepartment() : userRequest.getDepartment());
        profileDetails.setRole(userRequest.getRole() == null ? profileDetails.getRole() : userRequest.getRole());
        profileDetails.setPermission(userRequest.getPermission() == null ? profileDetails.getPermission() : userRequest.getPermission());
        repository.save(profileDetails);

        return profileDetails;
    }
}
