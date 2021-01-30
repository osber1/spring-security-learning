package com.learning.security.controller;

import com.learning.security.model.Book;
import com.learning.security.service.BookService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("api/books")
public class BookController {
    private final BookService service;

    @GetMapping
    public Collection<Book> getAllBooks() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Book getBook(@PathVariable int id) throws NotFoundException {
        return service.getOne(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.add(book);
    }
}
