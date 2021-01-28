package com.learning.security.service;

import com.learning.security.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
//    private final BookRepository repository;

    public Collection<Book> getALl() {
        return List.of(
                new Book(1,"Pirma knyga"),
                new Book(2,"Antra knyga"),
                new Book(3,"Tracia knyga"));
    }

}
