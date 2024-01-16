package com.book.book.service;

import com.book.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.book.book.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService
{
    @Autowired
    BookRepository bookRepository;

    @Override
    public Book saveBook(Book book)
    {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> saveBooks(List<Book> books)
    {
        return bookRepository.saveAll(books);
    }

    @Override
    public Book getBookById(long id)
    {
        Optional<Book> book = bookRepository.findById(id);

        Book emptyBook;
        if(book.isPresent())
        {
            emptyBook = book.get();
            return emptyBook;
        }
        else
        {
            throw new RuntimeException("Book Not Found");
        }
    }

    @Override
    public List<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Book book)
    {
        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        if (optionalBook.isPresent())
        {
            Book updateBook = optionalBook.get();
            updateBook.setTitle(book.getTitle());
            updateBook.setAuthor(book.getAuthor());
            updateBook.setPrice(book.getPrice());
            updateBook.setDetails(book.getDetails());
            updateBook.setImageUrl(book.getImageUrl());

            bookRepository.save(updateBook);
            return updateBook;
        }
        else
        {
            throw new RuntimeException("Book does not exist");
        }
    }

    @Override
    public void deleteBookById(long id)
    {
        bookRepository.deleteById(id);
    }

    public void deleteBooks()
    {
        bookRepository.deleteAll();
    }
}

