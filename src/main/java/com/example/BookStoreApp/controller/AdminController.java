package com.example.BookStoreApp.controller;

import com.example.BookStoreApp.entity.Book;
import com.example.BookStoreApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookRepository bookRepository;

    // ✅ Show all books on the dashboard
    @GetMapping("/dashboard")
    public String viewAllBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "admin/book-list"; // Show the table view
    }

    // ✅ Show form to add a new book
    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "admin/add-book";
    }

    // ✅ Save the book to DB
    @PostMapping("/add-book")
    public String saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/admin/dashboard"; // After saving, show updated book list
    }


    // ✅ Show the update book form
    @GetMapping("/edit-book/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id: " + id));
        model.addAttribute("book", book);
        return "admin/edit-book"; // Create this HTML
    }

    // ✅ Update the book after editing
    @PostMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book book) {
        book.setId(id);
        bookRepository.save(book);
        return "redirect:/admin/dashboard";
    }


    // ✅ Delete book by ID
    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }


}
