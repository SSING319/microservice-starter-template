package com.template.microservice.controller;

import com.template.microservice.document.User;
import com.template.microservice.model.RegisterRequest;
import com.template.microservice.repository.UserRepo;
import com.template.microservice.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api")
public class AuthController {
    private final UserRepo userRepo;
    private static final Logger logger = LogManager.getLogger(AuthController.class);

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(AuthUtil.encode(registerRequest.getPassword(), 12))
                .roles(registerRequest.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                        .collect(Collectors.toList()))
                .build();
        userRepo.save(user);
        logger.info("AuthController:register: User registered with username {}" , user.getUsername());
        return new ResponseEntity("User with Username: "+registerRequest.getUsername()+" created successfully", HttpStatus.CREATED);
    }
}
