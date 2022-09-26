package com.vttp.bookbackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vttp.bookbackend.models.Book;
import com.vttp.bookbackend.models.BookSummary;
import com.vttp.bookbackend.services.BookService;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookRESTController {
    @Autowired
    private BookService bookSvc;

    @GetMapping(path = "/books")
    public ResponseEntity<String> getBooks(
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        List<BookSummary> books = bookSvc.search(limit, offset);
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (BookSummary bookSummary : books) {
            arrayBuilder.add(bookSummary.toJson());
        }
        return ResponseEntity.ok().body(arrayBuilder.build().toString());
    }

    @GetMapping(path = "/book/{bookId}")
    public ResponseEntity<String> getBookbyId(@PathVariable String bookId) {
        Optional<Book> opt = bookSvc.getBook(bookId);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get().toJson().toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}