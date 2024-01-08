package com.template.microservice.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api")
@RequiredArgsConstructor
public class DummyController {
    private static final Logger logger = LogManager.getLogger(DummyController.class);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/testAdminAccess")
    public ResponseEntity<String> testAdminAccess(){
        logger.info("DummyController:testAdminAccess: working");
        return new ResponseEntity("Admin Access working", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/testUserAccess")
    public ResponseEntity<String> testUserAccess(){
        logger.info("DummyController:testUserAccess: working");
        return new ResponseEntity("User Access working", HttpStatus.OK);
    }

}
