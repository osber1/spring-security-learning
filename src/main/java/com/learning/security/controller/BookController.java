package com.learning.security.controller;

import com.learning.security.model.Book;
import com.learning.security.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/books")
public class BookController {

    private final BookService service;

    @GetMapping
    public Collection<Book> getAllBooks() {
        return service.getAll();
    }
}
