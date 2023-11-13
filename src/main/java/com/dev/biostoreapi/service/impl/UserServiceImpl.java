package com.dev.biostoreapi.service.impl;

import com.dev.biostoreapi.model.dto.UserLoginDTO;
import com.dev.biostoreapi.model.dto.UserRegistrationDTO;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.model.entity.UserRoleEntity;
import com.dev.biostoreapi.model.enums.UserRoleEnum;
import com.dev.biostoreapi.repository.UserRepository;
import com.dev.biostoreapi.service.UserRoleService;
import com.dev.biostoreapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        UserRoleEntity userRole = this.userRoleService.getUserRoleByEnumName(UserRoleEnum.ROLE_USER);
        UserEntity user = modelMapper.map(userRegistrationDTO, UserEntity.class);
        user.setRoles(List.of(userRole));
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setRegistrationDate(LocalDate.now());
        user.setActive(true);
        user.setProducts(new ArrayList<>());

        userRepository.save(user);
    }

//    @Override
//    public boolean loginUser(UserLoginDTO userLoginDTO) {
//
//        UserEntity user = this.userRepository.findByEmail(userLoginDTO.getEmail()).orElse(null);
//
//        boolean loginSuccess = false;
//        String encodedPassword = user.getPassword();
//
//        if(user != null && user.getRoles().stream()
//                .anyMatch(u -> u.getRole().equals(UserRoleEnum.USER))) {
//
//            String rawPassword = userLoginDTO.getPassword();
//
//
//            loginSuccess =  (encodedPassword != null) &&
//                   passwordEncoder.matches(rawPassword, encodedPassword);
//
//        }
//        return loginSuccess;
//    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
