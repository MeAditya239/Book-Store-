package com.example.BookStoreApp.controller;

import com.example.BookStoreApp.entity.Book;
import com.example.BookStoreApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.BookStoreApp.model.CartItem;
import jakarta.servlet.http.HttpSession;
import java.util.*;


import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BookRepository bookRepository;

    // ✅ User dashboard
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard"; // templates/user/dashboard.html
    }

    // ✅ Show all books to users
    @GetMapping("/books")
    public String viewBooksForUser(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "user/book-list"; // You must create this file
    }


    // ✅ Add book to cart
    @GetMapping("/add-to-cart/{bookId}")
    public String addToCart(@PathVariable Long bookId, HttpSession session) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            return "redirect:/user/books?error=BookNotFound";
        }

        // Get or create cart from session
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        // Add or update quantity
        if (cart.containsKey(bookId)) {
            cart.get(bookId).incrementQuantity();
        } else {
            cart.put(bookId, new CartItem(book));
        }

        session.setAttribute("cart", cart);
        return "redirect:/user/books";
    }


    // ✅ View cart
    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");
        model.addAttribute("cart", cart != null ? cart.values() : Collections.emptyList());
        return "user/cart"; // Create this HTML next
    }



    // ✅ Remove a book from cart
    @GetMapping("/remove-from-cart/{id}")
    public String removeFromCart(@PathVariable("id") Long bookId, HttpSession session) {
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");

        if (cart != null) {
            cart.remove(bookId);
            session.setAttribute("cart", cart); // update session cart
        }

        return "redirect:/user/cart";
    }


}
