package com.usdtl.ims.departmentMaster.master;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class ProfileDetailsController {
    private ProfileDetailsService service;

    @GetMapping("list")
    public List<ProfileDetailsEntity> getProfileDetails() {
        return service.getProfileDetails();
    }

    @PostMapping("sync")
    public List<ProfileDetailsEntity> syncProfileDetails(@RequestBody List<ProfileDetailsEntity> profileDetailsRequest) {
        return service.syncProfileDetails(profileDetailsRequest);
    }

    @GetMapping("{id}")
    public ProfileDetailsEntity getProfileDetail(@PathVariable(value = "id") String id) {
        return service.getProfileDetail(id);
    }

    @PostMapping("create")
    public List<ProfileDetailsEntity> createProfileDetail(@RequestBody List<ProfileDetailsEntity> usersRequest) {
        return service.createProfileDetail(usersRequest);
    }

    @PatchMapping("{id}/update")
    public ProfileDetailsEntity updateProfileDetail(@PathVariable(value = "id") String id, @RequestBody ProfileDetailsEntity profileDetailsRequest) {
        return service.updateProfileDetail(id, profileDetailsRequest);
    }
}
