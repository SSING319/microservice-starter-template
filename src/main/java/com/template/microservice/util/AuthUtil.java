package com.template.microservice.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@UtilityClass
public class AuthUtil {
    public String encode(String password, int strength){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
}
