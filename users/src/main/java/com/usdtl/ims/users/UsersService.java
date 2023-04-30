package com.usdtl.ims.users;

import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersService {
    private UsersRepository repository;

    public List<UsersEntity> getUsers() {
        return (List<UsersEntity>) repository.findAll();
    }

    public List<UsersEntity> createUsers(List<UsersEntity> usersRequest) {
        usersRequest.forEach(userRequest -> {
            boolean userExists = repository.existsById(userRequest.getId());
            if(!userExists) {
                UsersEntity newUser = UsersEntity.builder()
                        .id(userRequest.getId())
                        .department(null)
                        .role(null)
                        .build();
                repository.save(newUser);
            }
        });
        return (List<UsersEntity>) repository.findAll();
    }
}
