package com.book.book.service;

import com.book.book.domain.Book;

import java.util.List;

public interface BookService
{
    Book saveBook(Book book);
    List<Book> saveBooks(List<Book> books);
    Book getBookById(long id);
    List<Book> getAllBooks();
    Book updateBook(Book book);
    void deleteBookById(long id);
    void deleteBooks();
}

