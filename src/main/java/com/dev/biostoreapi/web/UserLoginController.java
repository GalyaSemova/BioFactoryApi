package com.dev.biostoreapi.web;

import com.dev.biostoreapi.model.dto.UserLoginDTO;
import com.dev.biostoreapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/login")
public class UserLoginController {

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<String> getLogin() {
        return ResponseEntity.ok("Welcome to the login page");
    }

    @PostMapping
    public ResponseEntity<String> postLogin(@RequestBody UserLoginDTO userLoginDTO) {
        boolean loginSuccessful = userService.loginUser(userLoginDTO);

        if (loginSuccessful) {
            return ResponseEntity.ok("User logged in successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }


}
