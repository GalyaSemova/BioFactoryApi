package com.dev.biostoreapi.service;

import com.dev.biostoreapi.model.dto.UserRegistrationDTO;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);
}
