package com.usdtl.ims.profileDetails;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class ProfileDetailsController {
    private ProfileDetailsService service;

    @GetMapping("list")
    public Page<ProfileDetailsEntity> getProfileDetails(@RequestParam(value = "page") Integer page) {
         return service.getProfileDetails(page);
    }

    @GetMapping("filter")
    public Page<ProfileDetailsEntity> filterProfileDetails(@RequestParam(value = "displayName") String displayName, @RequestParam(value = "page") Integer page) {
        return service.filterProfileDetails(displayName, page);
    }

    @PostMapping("sync")
    public Page<ProfileDetailsEntity> syncProfileDetails(@RequestBody List<ProfileDetailsEntity> profileDetailsRequest, @RequestParam(value = "page") Integer page) {
        return service.syncProfileDetails(profileDetailsRequest, page);
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
