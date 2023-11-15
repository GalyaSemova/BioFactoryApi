package com.dev.biostoreapi.service.impl;

import com.dev.biostoreapi.model.dto.UserRegistrationDTO;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.model.entity.UserRoleEntity;
import com.dev.biostoreapi.model.enums.UserRoleEnum;
import com.dev.biostoreapi.model.views.UserDashboardView;
import com.dev.biostoreapi.repository.ProductRepository;
import com.dev.biostoreapi.repository.UserRepository;
import com.dev.biostoreapi.service.UserRoleService;
import com.dev.biostoreapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;




    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder,
                           UserRoleService userRoleService)
    {
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



    @Override
    public UserEntity findByEmail(String email) {

        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserEntity findByUsername(String username) {

        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserDashboardView findById(Long userId) {

        Optional<UserEntity> user = userRepository.findById(userId);

        if(user.isPresent()) {
            UserDashboardView userData = modelMapper.map(user, UserDashboardView.class);

            return modelMapper.map(user, UserDashboardView.class);
        }

        return null;
    }

//    @Override
//    public List<UserEntity> getAllUsers() {
//
//        return userRepository.findAll();
//    }
}
