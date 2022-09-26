package com.vttp.bookbackend.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.vttp.bookbackend.models.Book;
import com.vttp.bookbackend.models.BookSummary;

@Repository
public class BookRepo {

    @Autowired
    private JdbcTemplate template;

    private static final String SQL_GET_BOOKS = "select book_id, title from book2018 order by title asc limit ? offset ?";
    private static final String SQL_GET_THIS_BOOK = "select * from book2018 where book_id =?";

    public List<BookSummary> getBooks(int limit, int offset) {
        List<BookSummary> books = new LinkedList<>();
        SqlRowSet rowSet = template.queryForRowSet(SQL_GET_BOOKS,
                limit,
                offset);
        while (rowSet.next()) {
            BookSummary b = BookSummary.createFromRowSet(rowSet);
            books.add(b);
        }
        return books;
    }

    public Optional<Book> getBookById(String bookId) {
        SqlRowSet rowSet = template.queryForRowSet(SQL_GET_THIS_BOOK, bookId);
        while (rowSet.next()) {
            return Optional.of(Book.createFromRowSet(rowSet));
        }
        return Optional.empty();
    }
}
