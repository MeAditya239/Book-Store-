package com.example.BookStoreApp.repository;

import com.example.BookStoreApp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
