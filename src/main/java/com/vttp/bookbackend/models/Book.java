package com.vttp.bookbackend.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Book {
    private String bookId;
    private String title;
    private String authors;
    private String description;
    private int pages;
    private float rating;

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return this.authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public static Book createFromRowSet(SqlRowSet rowSet) {
        Book book = new Book();
        book.setBookId(rowSet.getString("book_id"));
        book.setTitle(rowSet.getString("title"));
        book.setAuthors(rowSet.getString("authors"));
        book.setDescription(rowSet.getString("description"));
        book.setPages(rowSet.getInt("pages"));
        book.setRating(rowSet.getFloat("rating"));
        return book;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("bookid", bookId)
                .add("title", title)
                .add("authors", authors)
                .add("description", description)
                .add("pages", pages)
                .add("rating", rating)
                .build();
    }
}
