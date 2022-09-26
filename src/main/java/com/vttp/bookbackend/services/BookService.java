package com.vttp.bookbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vttp.bookbackend.models.Book;
import com.vttp.bookbackend.models.BookSummary;
import com.vttp.bookbackend.repositories.BookRepo;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public List<BookSummary> search(int limit, int offset) {
        return bookRepo.getBooks(limit, offset);
    }

    public Optional<Book> getBook(String bookId) {
        return bookRepo.getBookById(bookId);
    }
}
