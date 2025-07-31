package com.example.BookStoreApp.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {
    public static void main(String[] args) {
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void testPassword() {
        String raw = "test123";

        String encoded = passwordEncoder.encode(raw);
        System.out.println("🔐 BCrypt of 'test123' = " + encoded);
    }


    @PostConstruct
    public void init() {
        String encoded = passwordEncoder.encode("admin123");
        System.out.println("🔐 Admin Password (bcrypt): " + encoded);
    }

}


