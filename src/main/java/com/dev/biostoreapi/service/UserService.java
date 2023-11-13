package com.dev.biostoreapi.service;


import com.dev.biostoreapi.model.dto.UserRegistrationDTO;
import com.dev.biostoreapi.model.entity.UserEntity;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);


    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
