package com.vttp.bookbackend.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class BookSummary {
    private String bookId;
    private String title;

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

    public static BookSummary createFromRowSet(SqlRowSet rowSet) {
        BookSummary b = new BookSummary();
        b.setBookId(rowSet.getString("book_id"));
        b.setTitle(rowSet.getString("title"));
        return b;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("bookId", bookId)
                .add("title", title)
                .build();
    }
}
