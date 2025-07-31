package com.example.BookStoreApp.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private PasswordEncoder passwordEncoder;


    // ✅ Login page
    @GetMapping("/login")
    public String login() {
        return "login"; // maps to templates/login.html
    }

    // ✅ Redirect after successful login
    @GetMapping("/default")
    public String defaultAfterLogin(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        System.out.print(role);


        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard";
        } else if (role.equals("ROLE_USER")) {
            return "redirect:/user/dashboard";
        } else {
            return "redirect:/login?error";
        }
    }


    @PostConstruct
    public void testPassword() {
        String raw = "test123";
        String encoded = passwordEncoder.encode(raw);
        System.out.println("🔐 BCrypt of 'test123' = " + encoded);
    }

}
