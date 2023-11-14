package com.dev.biostoreapi.web;

import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.service.UserService;
import com.dev.biostoreapi.service.impl.securityImpl.UserDetailsServiceImpl;
import com.dev.biostoreapi.util.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtility;
    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl bioFactoryUserDetailsService;

    public UsersController(UserService userService, PasswordEncoder passwordEncoder, JwtUtils jwtUtility, AuthenticationManager authenticationManager, UserDetailsServiceImpl bioFactoryUserDetailsService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.bioFactoryUserDetailsService = bioFactoryUserDetailsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> getAllProducts() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

}
