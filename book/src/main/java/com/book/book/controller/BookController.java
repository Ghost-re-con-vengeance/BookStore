package com.book.book.controller;

import com.book.book.domain.Book;
import com.book.book.repository.BookRepository;
import com.book.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("message", "Welcome to the Bookstore!");
        return "home_page";
    }

    @GetMapping("/books")
    public String getAllBooks(Model model)
    {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "book_list";
    }

    @GetMapping("/books/{id}")
    public String getBookDetails(@PathVariable Long id, Model model)
    {
        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "book_details";
    }

    @GetMapping("/addForm")
    public String addBookForm(Model model)
    {
        Book book = new Book();
        model.addAttribute("book",book);
        return "add_book";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book)
    {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/updateForm/{id}")
    public String updateBookForm(@PathVariable Long id, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            model.addAttribute("book", optionalBook.get());
            return "update_book";
        } else {
            return "redirect:/books";
        }
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPrice(updatedBook.getPrice());
            existingBook.setDetails(updatedBook.getDetails());
            existingBook.setImageUrl(updatedBook.getImageUrl());

            bookRepository.save(existingBook);
            return "redirect:/books/" + id;
        } else {
            return "redirect:/books";
        }
    }

    @GetMapping("/deleteForm/{id}")
    public String deleteBookForm(@PathVariable Long id, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            model.addAttribute("book", optionalBook.get());
            return "delete_book";
        } else {
            return "redirect:/books";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
        }
        return "redirect:/books";
    }
}

