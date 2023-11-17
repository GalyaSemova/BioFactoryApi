package com.dev.biostoreapi.web.authControllers;

import com.dev.biostoreapi.config.LocalDateProvider;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.model.entity.UserRoleEntity;
import com.dev.biostoreapi.model.enums.UserRoleEnum;
import com.dev.biostoreapi.model.jwt.request.RegistrationRequest;
import com.dev.biostoreapi.model.jwt.response.MessageResponse;
import com.dev.biostoreapi.repository.UserRepository;
import com.dev.biostoreapi.repository.UserRoleRepository;
import com.dev.biostoreapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users/register")
//@CrossOrigin("http://localhost:3000")
public class UserRegistrationController {

    private final UserService userService;


    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository roleRepository;
    @Autowired
    private LocalDateProvider localDateProvider;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> getRegister() {

        return ResponseEntity.ok("Welcome to the registration page");
    }

@PostMapping
public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest signUpRequest,
                                      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(validationErrors);
    }


    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Email is already in use!"));
    }
    if(!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Passwords do not match!"));

    }

    // Create new user's account
    UserEntity user = new UserEntity(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getFirstName(),
            signUpRequest.getLastName(),
            signUpRequest.getAddress(),
            signUpRequest.getPhoneNumber()
    );

    user.setRegistrationDate(localDateProvider.now());
    user.setProducts(new ArrayList<>());
    user.setActive(true);

    List<String> strRoles = signUpRequest.getRole();
    List<UserRoleEntity> roles = new ArrayList<>();

    if (strRoles == null) {
        UserRoleEntity userRole = roleRepository.findByName(UserRoleEnum.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
    } else {
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    UserRoleEntity adminRole = roleRepository.findByName(UserRoleEnum.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);

                    break;
                case "mod":
                    UserRoleEntity modRole = roleRepository.findByName(UserRoleEnum.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);

                    break;
                default:
                    UserRoleEntity userRole = roleRepository.findByName(UserRoleEnum.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
            }
        });
    }

    user.setRoles(roles);
    userRepository.save(user);

//    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
}
}
