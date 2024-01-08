package com.template.microservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private List<String> roles;
}
