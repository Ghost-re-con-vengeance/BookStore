package com.book.book.controller.api;
import com.book.book.domain.Book;
import com.book.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookAPIController
{
    @Autowired
    BookService bookService;

    @GetMapping("/getBooks")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id)
    {
        return ResponseEntity.ok().body(bookService.getBookById(id));
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> createBook(@RequestBody Book book)
    {
        return ResponseEntity.ok().body(bookService.saveBook(book));
    }

    @PostMapping("/addBooks")
    public List<Book> addBooks(@RequestBody List<Book> books)
    {
        return bookService.saveBooks(books);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book)
    {
        return ResponseEntity.ok().body(bookService.updateBook(book));
    }

    @DeleteMapping("/deleteBook/{id}")
    public HttpStatus deleteBook(@PathVariable long id)
    {
        bookService.deleteBookById(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/deleteBooks")
    public String deleteBooks()
    {
        bookService.deleteBooks();
        return "All Books Deleted";
    }

}

