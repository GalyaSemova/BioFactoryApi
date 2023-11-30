package com.dev.biostoreapi.testUtils;

import com.dev.biostoreapi.model.entity.UserRoleEntity;
import com.dev.biostoreapi.model.enums.UserRoleEnum;
import com.dev.biostoreapi.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class dbInit implements CommandLineRunner {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Override
    public void run(String... args) throws Exception {
        UserRoleEntity roleUser = new UserRoleEntity();
        UserRoleEntity roleAdmin = new UserRoleEntity();
        UserRoleEntity roleModerator = new UserRoleEntity();
        roleUser.setName(UserRoleEnum.ROLE_USER);
        roleAdmin.setName(UserRoleEnum.ROLE_ADMIN);
        roleModerator.setName(UserRoleEnum.ROLE_MODERATOR);


        if(userRoleRepository.count() == 0) {
            userRoleRepository.saveAll(List.of(
                    roleUser,
                    roleAdmin,
                    roleModerator
            ));
        }
    }
}
