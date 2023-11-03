package com.dev.biostoreapi.web;


import com.dev.biostoreapi.model.dto.UserRegistrationDTO;
import com.dev.biostoreapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/register")
//@CrossOrigin("http://localhost:3000")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> getRegister() {
        return ResponseEntity.ok("Welcome to the registration page");
    }

    @PostMapping
    public ResponseEntity<String> postRegister(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        userService.registerUser(userRegistrationDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
