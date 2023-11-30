package com.dev.biostoreapi.testUtils;

import com.dev.biostoreapi.config.LocalDateProvider;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.model.entity.UserRoleEntity;
import com.dev.biostoreapi.model.enums.UserRoleEnum;
import com.dev.biostoreapi.repository.UserRepository;
import com.dev.biostoreapi.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTestDataUtil {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private LocalDateProvider mockLocalDateProvider;



    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setName(UserRoleEnum.ROLE_ADMIN);
            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setName(UserRoleEnum.ROLE_USER);
            UserRoleEntity moderatorRole = new UserRoleEntity();
            moderatorRole.setName(UserRoleEnum.ROLE_MODERATOR);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(moderatorRole);
            userRoleRepository.save(userRole);
        }
    }

    public UserEntity createTestUser(String email) {

        initRoles();

        UserEntity newUser = new UserEntity();
        newUser.setActive(true);
        newUser.setEmail(email);
        newUser.setUsername("Test");
        newUser.setFirstName("FirstName");
        newUser.setLastName("LastName");
        newUser.setPhoneNumber("87865756653");
        newUser.setRegistrationDate(mockLocalDateProvider.now());
        newUser.setRoles(userRoleRepository.findByName(UserRoleEnum.ROLE_USER).stream().toList());

        return userRepository.save(newUser);

    }

//    private UserEntity createUser(String email, List<UserRoleEnum> roles) {
//        var roleEntities = userRoleRepository.findByName(UserRoleEnum.ROLE_USER).stream().toList();
//        UserEntity newUser = new UserEntity();
//        newUser.setActive(true);
//        newUser.setEmail(email);
//        newUser.setFirstName("Test user first");
//        newUser.setLastName("Test user Last");
//        newUser.setRoles(roleEntities);
//
//        return userRepository.save(newUser);
//
//    }



    public void cleanUp() {
        userRepository.deleteAll();
    }


}
