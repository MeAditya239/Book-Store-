package com.example.BookStoreApp.security;

import com.example.BookStoreApp.entity.User;
import com.example.BookStoreApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        System.out.println("🔍 Attempting login with phone: " + phone);

        User user = userRepository.findByPhone(phone);
        if (user == null) {
            System.out.println("❌ Phone not found: " + phone);
            throw new UsernameNotFoundException("Invalid phone or password");
        }

        System.out.println("✅ User found: " + user.getPhone());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getPhone())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

}
