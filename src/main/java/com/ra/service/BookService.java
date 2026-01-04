package com.ra.service;

import com.ra.model.Book;
import com.ra.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(String search) {
        return bookRepository.getAllBooks(search);
    }

    public Book findById(long id) {
        return bookRepository.getBookById(id);
    }

    public Book save(Book book) {
        return bookRepository.saveBook(book);
    }

    public Book update(Book book, long id) {
        Book oldBook = bookRepository.getBookById(id);
        if (oldBook != null) {
            book.setId(id);
            return bookRepository.saveBook(book);
        } else {
            return null;
        }
    }

    public boolean deleteById(long id) {
        Book delBook = bookRepository.getBookById(id);
        if (delBook != null) {
            return bookRepository.deleteBook(delBook);
        } else {
            return false;
        }
    }
}
