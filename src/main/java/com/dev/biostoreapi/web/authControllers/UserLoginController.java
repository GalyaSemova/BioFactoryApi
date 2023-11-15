package com.dev.biostoreapi.web.authControllers;

import com.dev.biostoreapi.model.jwt.request.LoginRequest;
import com.dev.biostoreapi.model.jwt.response.JwtResponse;
import com.dev.biostoreapi.repository.UserRepository;
import com.dev.biostoreapi.repository.UserRoleRepository;

import com.dev.biostoreapi.service.impl.securityImpl.UserDetailsImpl;
import com.dev.biostoreapi.util.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users/login")
public class UserLoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping
    public ResponseEntity<String> getLogin() {

        return ResponseEntity.ok("Welcome to the login page");
    }


    @PostMapping()
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

//        return ResponseEntity.ok(new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getUsername(),
//                userDetails.getEmail(),
//                roles,
//                userDetails.getFirstName(),
//                userDetails.getLastName(),
//                userDetails.getPhoneNumber(),
//                userDetails.getAddress()));
    }




//    @PostMapping
//    public String logInUser(@RequestParam String email) {
//        UserEntity userByEmail = this.userService.findByEmail(email);
//        if (userByEmail.getRoles().stream()
//                .anyMatch(u -> u.getRole().equals(UserRoleEnum.USER))) {
//            return "USER";
//        }
//
//        return null;
//    }

//    @PostMapping("/login")
//    public ResponseEntity<?> logInUser(@RequestParam String email) {
//        UserEntity userByEmail = this.userService.findByEmail(email);
//
//        if (userByEmail.getRoles().stream().anyMatch(u -> u.getRole().equals(UserRoleEnum.USER))) {
//            Map<String, Object> response = new HashMap<>();
//            response.put("status", "success");
//            response.put("message", "User authenticated successfully");
//            response.put("role", "USER");
//            return ResponseEntity.ok(response);
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("status", "error");
//        response.put("message", "Authentication failed");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//    }

//    @PostMapping
//    public ResponseEntity<String> postLogin(@RequestBody UserLoginDTO userLoginDTO) {
//        boolean loginSuccessful = userService.loginUser(userLoginDTO);
//
//        if (loginSuccessful) {
//            return ResponseEntity.ok("User logged in successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
//        }
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("User login successfully!...", HttpStatus.OK);
//    }

//    @PostMapping
//    @CrossOrigin(origins = "http://localhost:3000")
////    @Operation(summary = "Login based on user role after authentication", security = @SecurityRequirement(name = "bearerAuth"))
//    public String logInUser(@RequestParam UserLoginDTO userLoginDTO) {
//        UserEntity userByEmail = this.userService.findByEmail(userLoginDTO.getEmail());
//
//        String rawPassword = userLoginDTO.getPassword();
//
//        if(userByEmail != null) {
//            String encodedPassword = userByEmail.getPassword();
//
//            if((encodedPassword != null) &&
//                    passwordEncoder.matches(rawPassword, encodedPassword)) {
//                return "Passwords match";
//            }
//
//            if (userByEmail.getRoles().stream()
//                    .anyMatch(u -> u.getRole().equals(UserRoleEnum.USER))) {
//                return "USER";
//            }
//        }
//
//        return null;
//    }


}
