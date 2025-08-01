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

    // ✅ Admin dashboard
    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin/dashboard"; // templates/admin/dashboard.html
    }

    // ✅ Show all books to admin
    @GetMapping("/books")
    public String viewBooksForAdmin(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "admin/book-list"; // templates/admin/book-list.html
    }

    // ✅ Show form to add a new book
    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "admin/add-book";
    }

    // ✅ Save book
    @PostMapping("/add-book")
    public String saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/admin/books";
    }

    // ✅ Edit book form
    @GetMapping("/edit-book/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id: " + id));
        model.addAttribute("book", book);
        return "admin/edit-book";
    }

    // ✅ Update book
    @PostMapping("/update-book/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookRepository.save(book);
        return "redirect:/admin/books";
    }

    // ✅ Delete book
    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/admin/books";
    }

// for debugging
    @GetMapping("/test")
    public String test() {
        return "admin/book-list"; // Does it render now?
    }

}
