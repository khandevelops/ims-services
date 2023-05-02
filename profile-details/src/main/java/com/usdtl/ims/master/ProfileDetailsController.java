package com.usdtl.ims.master;

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

    @GetMapping("{id}")
    public ProfileDetailsEntity getProfileDetail(@PathVariable(value = "id") String id) {
        return service.getProfileDetail(id);
    }

    @PostMapping("create")
    public List<ProfileDetailsEntity> createUsers(@RequestBody List<ProfileDetailsEntity> usersRequest) {
        return service.createUsers(usersRequest);
    }

    @PatchMapping("{id}/update")
    public ProfileDetailsEntity updateUser(@PathVariable(value = "id") String id, @RequestBody ProfileDetailsEntity userRequest) {
        return service.updateUser(id, userRequest);
    }
}
