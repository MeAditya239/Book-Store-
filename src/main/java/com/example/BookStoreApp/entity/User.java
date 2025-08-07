package com.example.BookStoreApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user") // "user" is reserved keyword in MySQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String phone;

    private String password;

    private String role; // ADMIN or USER


    @Version
    private Long version;

    // Getters and Setters
    // Getters and setters for version
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
