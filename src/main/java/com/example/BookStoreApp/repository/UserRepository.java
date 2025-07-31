package com.example.BookStoreApp.repository;


import com.example.BookStoreApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhone(String phone);
}
