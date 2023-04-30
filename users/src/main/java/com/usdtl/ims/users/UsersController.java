package com.usdtl.ims.users;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class UsersController {
    private UsersService service;

    @GetMapping("list")
    public List<UsersEntity> getUsers() {
        return service.getUsers();
    }

    @PostMapping("create")
    public List<UsersEntity> createUsers(@RequestBody List<UsersEntity> usersRequest) {
        return service.createUsers(usersRequest);
    }
}
